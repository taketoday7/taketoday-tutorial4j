package cn.tuyucheng.taketoday.webclient.clientcredentials;

import ch.qos.logback.classic.spi.ILoggingEvent;
import cn.tuyucheng.taketoday.webclient.clientcredentials.service.WebClientChonJob;
import cn.tuyucheng.taketoday.webclient.utils.ListAppender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Note: this Live test requires the Authorization Service and the Resource service located in the Tuyucheng/spring-security-oauth repo
 *
 * @author tuyucheng
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ClientCredentialsOauthApplication.class})
public class OAuth2ClientCredentialsLiveTest {

   @Autowired
   WebClientChonJob service;

   @Before
   public void clearLogList() {
      ListAppender.clearEventList();
   }

   @Test
   public void givenFooWithNullId_whenProcessFoo_thenLogsWithDebugTrace() throws Exception {
      service.logResourceServiceResponse();

      Thread.sleep(3000);

      Collection<String> allLoggedEntries = ListAppender.getEvents()
            .stream()
            .map(ILoggingEvent::getFormattedMessage)
            .collect(Collectors.toList());
      assertThat(allLoggedEntries).anyMatch(entry -> entry.contains("We retrieved the following resource using Client Credentials Grant Type: {\"id\""));
   }
}