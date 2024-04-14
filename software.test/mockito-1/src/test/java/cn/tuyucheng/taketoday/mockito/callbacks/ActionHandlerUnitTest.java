package cn.tuyucheng.taketoday.mockito.callbacks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class ActionHandlerUnitTest {

   @Mock
   private Service service;

   @Captor
   private ArgumentCaptor<Callback<Response>> callbackCaptor;

   @BeforeEach
   public void setup() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   public void givenServiceWithValidResponse_whenCallbackReceived_thenProcessed() {
      ActionHandler handler = new ActionHandler(service);
      handler.doAction();

      verify(service).doAction(anyString(), callbackCaptor.capture());

      Callback<Response> callback = callbackCaptor.getValue();
      Response response = new Response();
      callback.reply(response);

      String expectedMessage = "Successful data response";
      Data data = response.getData();
      assertEquals(expectedMessage, data.message(), "Should receive a successful message: ");
   }

   @Test
   public void givenServiceWithInvalidResponse_whenCallbackReceived_thenNotProcessed() {
      Response response = new Response();
      response.setIsValid(false);

      doAnswer((Answer<Void>) invocation -> {
         Callback<Response> callback = invocation.getArgument(1);
         callback.reply(response);

         Data data = response.getData();
         assertNull(data, "No data in invalid response: ");
         return null;
      }).when(service)
            .doAction(anyString(), any(Callback.class));

      ActionHandler handler = new ActionHandler(service);
      handler.doAction();
   }
}