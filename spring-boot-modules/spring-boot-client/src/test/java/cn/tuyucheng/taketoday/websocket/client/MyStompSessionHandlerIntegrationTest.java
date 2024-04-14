package cn.tuyucheng.taketoday.websocket.client;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;

class MyStompSessionHandlerIntegrationTest {

   @Test
   void givenValidSession_whenConnected_SendsMessage() {
      StompSession mockSession = Mockito.mock(StompSession.class);
      StompHeaders mockHeader = Mockito.mock(StompHeaders.class);
      MyStompSessionHandler sessionHandler = new MyStompSessionHandler();
      sessionHandler.afterConnected(mockSession, mockHeader);
      Mockito.verify(mockSession).subscribe("/topic/messages", sessionHandler);
      Mockito.verify(mockSession).send(Mockito.anyString(), Mockito.any());
   }
}