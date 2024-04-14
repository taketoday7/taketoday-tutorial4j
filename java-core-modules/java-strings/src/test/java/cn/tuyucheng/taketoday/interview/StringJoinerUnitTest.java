package cn.tuyucheng.taketoday.interview;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringJoinerUnitTest {
   @Test
   public void whenUsingStringJoiner_thenStringsJoined() {
      StringJoiner joiner = new StringJoiner(",", "[", "]");
      joiner.add("Red")
            .add("Green")
            .add("Blue");

      assertEquals(joiner.toString(), "[Red,Green,Blue]");
   }
}
