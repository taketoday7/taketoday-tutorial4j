package cn.tuyucheng.taketoday.spring.cloud.stream.rabbit;

import cn.tuyucheng.taketoday.spring.cloud.stream.rabbit.processor.MyProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MultipleOutputsWithConditionsServiceApplication.class)
@DirtiesContext
class MultipleOutputsWithConditionsServiceIntegrationTest {

   @Autowired
   private MyProcessor pipe;

   @Autowired
   private MessageCollector messageCollector;

   @Test
   void whenSendMessage_thenResponseIsInAOutput() {
      whenSendMessage(1);
      thenPayloadInChannelIs(pipe.anOutput(), "1");
   }

   @Test
   void whenSendMessage_thenResponseIsInAnotherOutput() {
      whenSendMessage(11);
      thenPayloadInChannelIs(pipe.anotherOutput(), "11");
   }

   private void whenSendMessage(Integer val) {
      pipe.myInput()
            .send(MessageBuilder.withPayload(val)
                  .build());
   }

   private void thenPayloadInChannelIs(MessageChannel channel, String expectedValue) {
      Object payload = messageCollector.forChannel(channel)
            .poll()
            .getPayload();
      assertEquals(expectedValue, payload);
   }
}