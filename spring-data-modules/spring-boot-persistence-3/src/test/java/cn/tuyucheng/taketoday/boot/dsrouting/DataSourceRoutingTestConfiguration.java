package cn.tuyucheng.taketoday.boot.dsrouting;

import cn.tuyucheng.taketoday.dsrouting.ClientDao;
import cn.tuyucheng.taketoday.dsrouting.ClientDataSourceRouter;
import cn.tuyucheng.taketoday.dsrouting.ClientDatabase;
import cn.tuyucheng.taketoday.dsrouting.ClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceRoutingTestConfiguration {

   @Bean
   public ClientService clientService() {
      return new ClientService(new ClientDao(clientDatasource()));
   }

   @Bean
   public DataSource clientDatasource() {
      Map<Object, Object> targetDataSources = new HashMap<>();
      DataSource clientADatasource = clientADatasource();
      DataSource clientBDatasource = clientBDatasource();
      targetDataSources.put(ClientDatabase.CLIENT_A, clientADatasource);
      targetDataSources.put(ClientDatabase.CLIENT_B, clientBDatasource);

      ClientDataSourceRouter clientRoutingDatasource = new ClientDataSourceRouter();
      clientRoutingDatasource.setTargetDataSources(targetDataSources);
      clientRoutingDatasource.setDefaultTargetDataSource(clientADatasource);
      return clientRoutingDatasource;
   }

   private DataSource clientADatasource() {
      EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
      return dbBuilder.setType(EmbeddedDatabaseType.H2).setName("CLIENT_A").addScript("dsrouting-db.sql").build();
   }

   private DataSource clientBDatasource() {
      EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
      return dbBuilder.setType(EmbeddedDatabaseType.H2).setName("CLIENT_B").addScript("dsrouting-db.sql").build();
   }
}