package cn.tuyucheng.taketoday.spring.kafka.retryable;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = "multitype")
public class MultiTypeKafkaListener {

   @KafkaHandler
   // @RetryableTopic(backoff = @Backoff(value = 3000L), attempts = "5", autoCreateTopics = "false", include = SocketTimeoutException.class, exclude = NullPointerException.class)
   public void handleGreeting(Greeting greeting) {
      if (greeting.getName().equalsIgnoreCase("test")) {
         throw new MessagingException("test not allowed");
      }
      System.out.println(STR."Greeting received: \{greeting}");
   }

   @KafkaHandler
   public void handleF(Farewell farewell) {
      System.out.println(STR."Farewell received: \{farewell}");
   }

   @KafkaHandler(isDefault = true)
   public void unknown(Object object) {
      System.out.println(STR."Unknown type received: \{object}");
   }
}