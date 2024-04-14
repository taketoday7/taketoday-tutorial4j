package cn.tuyucheng.taketoday.boot.documentation.springwolf.adapter.outgoing;

import cn.tuyucheng.taketoday.boot.documentation.springwolf.dto.OutgoingPayloadDto;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncOperation;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.AsyncPublisher;
import io.github.stavshamir.springwolf.asyncapi.scanners.channels.operationdata.annotation.KafkaAsyncOperationBinding;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME;

@AllArgsConstructor
@Component
@Slf4j
public class OutgoingProducer {

   private static final String TOPIC_NAME = "outgoing-topic";

   private final KafkaTemplate<String, OutgoingPayloadDto> kafkaTemplate;

   @AsyncPublisher(
         operation = @AsyncOperation(
               channelName = TOPIC_NAME,
               description = "More details for the outgoing topic",
               headers = @AsyncOperation.Headers(
                     schemaName = "SpringKafkaDefaultHeadersOutgoingPayloadDto",
                     values = {
                           // this header is generated by Spring by default
                           @AsyncOperation.Headers.Header(
                                 name = DEFAULT_CLASSID_FIELD_NAME,
                                 description = "Spring Type Id Header",
                                 value = "cn.tuyucheng.taketoday.boot.documentation.springwolf.dto.OutgoingPayloadDto"
                           ),
                     }
               )
         )
   )
   @KafkaAsyncOperationBinding
   public void publish(OutgoingPayloadDto payload) {
      LOGGER.info("Publishing new message: {}", payload.toString());

      kafkaTemplate.send(TOPIC_NAME, payload);
   }
}