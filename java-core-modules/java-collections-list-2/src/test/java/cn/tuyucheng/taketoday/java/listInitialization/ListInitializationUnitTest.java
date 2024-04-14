package cn.tuyucheng.taketoday.java.listInitialization;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
public class ListInitializationUnitTest {

   @Test
   public void givenAnonymousInnerClass_thenInitialiseList() {
      List<String> cities = new ArrayList() {
         {
            add("New York");
            add("Rio");
            add("Tokyo");
         }
      };

      Assertions.assertTrue(cities.contains("New York"));
   }

   @Test
   public void givenArraysAsList_thenInitialiseList() {
      List<String> list = Arrays.asList("foo", "bar");

      Assertions.assertTrue(list.contains("foo"));
   }

   @Test(expected = UnsupportedOperationException.class)
   public void givenArraysAsList_whenAdd_thenUnsupportedException() {
      List<String> list = Arrays.asList("foo", "bar");

      list.add("baz");
   }

   @Test
   public void givenArraysAsList_whenCreated_thenShareReference() {
      String[] array = {"foo", "bar"};
      List<String> list = Arrays.asList(array);
      array[0] = "baz";
      Assertions.assertEquals("baz", list.get(0));
   }

   @Test
   public void givenStream_thenInitializeList() {
      List<String> list = Stream.of("foo", "bar")
            .collect(Collectors.toList());

      Assertions.assertTrue(list.contains("foo"));
   }
}
