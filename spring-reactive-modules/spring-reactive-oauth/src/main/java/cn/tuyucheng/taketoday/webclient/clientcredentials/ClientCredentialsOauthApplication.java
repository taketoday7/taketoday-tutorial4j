package cn.tuyucheng.taketoday.webclient.clientcredentials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Note: This app is configured to use the authorization service and the resource service located in Tuyucheng/spring-security-oauth repo
 *
 * @author tuyucheng
 */
@PropertySource("classpath:webclient-client-credentials-oauth-application.properties")
@EnableScheduling
@SpringBootApplication
public class ClientCredentialsOauthApplication {

   public static void main(String[] args) {
      SpringApplication.run(ClientCredentialsOauthApplication.class, args);
   }
}