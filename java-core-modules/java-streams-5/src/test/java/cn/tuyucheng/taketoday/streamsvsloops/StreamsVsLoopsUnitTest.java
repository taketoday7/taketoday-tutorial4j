package cn.tuyucheng.taketoday.streamsvsloops;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StreamsVsLoopsUnitTest {

   @Test
   public void givenListWithFruits_whenFilterUsingStream_thenReturnedCount() {
      List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape");
      long count = fruits.stream()
            .filter(fruit -> fruit.length() > 5)
            .count();
      assertEquals(2, count);
   }

   @Test
   public void givenListWithFruits_whenFilterUsingForLoop_thenReturnedCount() {
      List<String> fruits = Arrays.asList("apple", "banana", "orange", "grape");
      long count = 0;
      for (String fruit : fruits) {
         if (fruit.length() > 5) {
            count++;
         }
      }
      assertEquals(2, count);
   }

   @Test
   public void givenListWithFruits_whenApplyUppercase_thenNewListGenerated() {
      List<String> desiredArray = new ArrayList<>(Arrays.asList("APPLE", "BANANA", "ORANGE"));
      List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
      List<String> upperCaseFruits = fruits.stream()
            .map(fruit -> fruit.toUpperCase())
            .collect(Collectors.toList());
      assertThat(desiredArray, is(upperCaseFruits));
   }

   @Test
   public void givenListWithFruits_whenApplyUppercase_elementsAreUpdated() {
      List<String> desiredArray = new ArrayList<>(Arrays.asList("APPLE", "BANANA", "ORANGE"));
      List<String> fruits = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
      for (int i = 0; i < fruits.size(); i++) {
         fruits.set(i, fruits.get(i).toUpperCase());
      }
      assertThat(desiredArray, is(fruits));
   }
}
