package cn.tuyucheng.taketoday.kafka.streams;

import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

class WordCountProcessorUnitTest {

   private WordCountProcessor wordCountProcessor;

   @BeforeEach
   void setUp() {
      wordCountProcessor = new WordCountProcessor();
   }

   @Test
   void givenInputMessages_whenProcessed_thenWordCountIsProduced() {
      StreamsBuilder streamsBuilder = new StreamsBuilder();
      wordCountProcessor.buildPipeline(streamsBuilder);
      Topology topology = streamsBuilder.build();

      try (TopologyTestDriver topologyTestDriver = new TopologyTestDriver(topology, new Properties())) {

         TestInputTopic<String, String> inputTopic = topologyTestDriver
               .createInputTopic("input-topic", new StringSerializer(), new StringSerializer());

         TestOutputTopic<String, Long> outputTopic = topologyTestDriver
               .createOutputTopic("output-topic", new StringDeserializer(), new LongDeserializer());

         inputTopic.pipeInput("key", "hello world");
         inputTopic.pipeInput("key2", "hello");

         assertThat(outputTopic.readKeyValuesToList())
               .containsExactly(
                     KeyValue.pair("hello", 1L),
                     KeyValue.pair("world", 1L),
                     KeyValue.pair("hello", 2L)
               );
      }
   }
}