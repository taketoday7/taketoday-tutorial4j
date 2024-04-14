package cn.tuyucheng.taketoday;

import cn.tuyucheng.taketoday.filter.RequestValidatorFilter;
import cn.tuyucheng.taketoday.handler.EmployeeHandler;
import cn.tuyucheng.taketoday.handler.RedirectHandler;
import cn.tuyucheng.taketoday.model.Employee;
import cn.tuyucheng.taketoday.repository.EmployeeRepository;
import cn.tuyucheng.taketoday.repository.EmployeeRepositoryImpl;
import com.zaxxer.hikari.HikariConfig;
import io.netty.buffer.PooledByteBufAllocator;
import ratpack.exec.internal.DefaultExecController;
import ratpack.func.Action;
import ratpack.func.Function;
import ratpack.guice.BindingsSpec;
import ratpack.guice.Guice;
import ratpack.handling.Chain;
import ratpack.hikari.HikariModule;
import ratpack.http.client.HttpClient;
import ratpack.jackson.Jackson;
import ratpack.registry.Registry;
import ratpack.server.RatpackServer;
import ratpack.server.RatpackServerSpec;
import ratpack.server.ServerConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Application {

   public static void main(String[] args) throws Exception {

      final Action<HikariConfig> hikariConfigAction = hikariConfig -> {
         hikariConfig.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");
         hikariConfig.addDataSourceProperty("URL", "jdbc:h2:mem:tuyucheng;INIT=RUNSCRIPT FROM 'classpath:/DDL.sql'");
      };

      final Action<BindingsSpec> bindingsSpecAction = bindings -> bindings.module(HikariModule.class, hikariConfigAction);
      final HttpClient httpClient = HttpClient.of(httpClientSpec -> {
         httpClientSpec.poolSize(10)
               .connectTimeout(Duration.of(60, ChronoUnit.SECONDS))
               .maxContentLength(ServerConfig.DEFAULT_MAX_CONTENT_LENGTH)
               .responseMaxChunkSize(16384)
               .readTimeout(Duration.of(60, ChronoUnit.SECONDS))
               .byteBufAllocator(PooledByteBufAllocator.DEFAULT).execController(new DefaultExecController(2));
      });
      final Function<Registry, Registry> registryFunction = Guice.registry(bindingsSpecAction);

      final Action<Chain> chainAction = chain -> chain.all(new RequestValidatorFilter())
            .get(ctx -> ctx.render("Welcome to tuyucheng ratpack!!!"))
            .get("data/employees", ctx -> ctx.render(Jackson.json(createEmpList())))
            .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens()
                  .get("name") + "!!!"))
            .post(":amount", ctx -> ctx.render(" Amount $" + ctx.getPathTokens()
                  .get("amount") + " added successfully !!!"));

      final Action<Chain> routerChainAction = routerChain -> {
         routerChain.path("redirect", new RedirectHandler())
               .prefix("employee", empChain -> {
                  empChain.get(":id", new EmployeeHandler());
               });
      };
      final Action<RatpackServerSpec> ratpackServerSpecAction = serverSpec -> serverSpec.registry(registryFunction)
            .registryOf(registrySpec -> {
               registrySpec.add(EmployeeRepository.class, new EmployeeRepositoryImpl());
               registrySpec.add(HttpClient.class, httpClient);
            })
            .handlers(chain -> chain.insert(routerChainAction)
                  .insert(chainAction));

      RatpackServer.start(ratpackServerSpecAction);
   }

   private static List<Employee> createEmpList() {
      List<Employee> employees = new ArrayList<>();
      employees.add(new Employee(1L, "Mr", "John Doe"));
      employees.add(new Employee(2L, "Mr", "White Snow"));
      return employees;
   }

}
