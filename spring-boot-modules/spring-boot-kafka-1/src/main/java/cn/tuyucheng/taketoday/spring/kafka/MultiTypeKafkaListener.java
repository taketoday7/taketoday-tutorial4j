package cn.tuyucheng.taketoday.spring.kafka;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = "multitype")
public class MultiTypeKafkaListener {

   @KafkaHandler
   public void handleGreeting(Greeting greeting) {
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