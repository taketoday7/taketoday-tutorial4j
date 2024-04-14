package cn.tuyucheng.taketoday.streams.debug;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Example1 {
   @Test
   public void whenDebugging_thenInformationIsShown() {
      int[] listOutputSorted = IntStream.of(-3, 10, -4, 1, 3)
            .sorted()
            .toArray();

      assertThat(listOutputSorted).isSorted();
   }
}
