package cn.tuyucheng.taketoday.ldap.javaconfig;

import cn.tuyucheng.taketoday.ldap.client.LdapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"cn.tuyucheng.taketoday.ldap.*"})
@Profile("default")
@EnableLdapRepositories(basePackages = "cn.tuyucheng.taketoday.ldap.**")
public class AppConfig {

   @Autowired
   private Environment env;

   @Bean
   public LdapContextSource contextSource() {
      LdapContextSource contextSource = new LdapContextSource();
      contextSource.setUrl(env.getRequiredProperty("ldap.url"));
      contextSource.setBase(env.getRequiredProperty("ldap.partitionSuffix"));
      contextSource.setUserDn(env.getRequiredProperty("ldap.principal"));
      contextSource.setPassword(env.getRequiredProperty("ldap.password"));
      return contextSource;
   }

   @Bean
   public LdapTemplate ldapTemplate() {
      return new LdapTemplate(contextSource());
   }

   @Bean
   public LdapClient ldapClient() {
      return new LdapClient();
   }

}
