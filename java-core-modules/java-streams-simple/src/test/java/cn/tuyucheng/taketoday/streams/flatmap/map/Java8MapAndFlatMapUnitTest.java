package cn.tuyucheng.taketoday.streams.flatmap.map;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;


class Java8MapAndFlatMapUnitTest {

   @Test
   void givenStream_whenCalledMap_thenProduceList() {
      List<String> myList = Stream.of("a", "b")
            .map(String::toUpperCase)
            .collect(Collectors.toList());
      assertEquals(asList("A", "B"), myList);
   }

   @Test
   void givenStream_whenCalledFlatMap_thenProduceFlattenedList() {
      List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
      System.out.println(list);

      System.out.println(list.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList()));
   }

   @Test
   void givenOptional_whenCalledMap_thenProduceOptional() {
      Optional<String> s = Optional.of("test");
      assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
   }

   @Test
   void givenOptional_whenCalledFlatMap_thenProduceFlattenedOptional() {
      assertEquals(Optional.of(Optional.of("STRING")), Optional.of("string")
            .map(s -> Optional.of("STRING")));

      assertEquals(Optional.of("STRING"), Optional.of("string")
            .flatMap(s -> Optional.of("STRING")));
   }

}
