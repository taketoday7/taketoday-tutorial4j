package cn.tuyucheng.taketoday.collections.combiningcollections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CombiningListsUnitTest {
   private static final List<Object> first = Arrays.asList(new Object[]{
         "One",
         "Two",
         "Three"
   });

   private static final List<Object> second = Arrays.asList(new Object[]{
         "Four",
         "Five",
         "Six"
   });

   private static final List<Object> expected = Arrays.asList(new Object[]{
         "One",
         "Two",
         "Three",
         "Four",
         "Five",
         "Six"
   });

   @Test
   public void givenTwoLists_whenUsingNativeJava_thenArraysCombined() {
      assertThat(CombiningLists.usingNativeJava(first, second), is(expected));
   }

   @Test
   public void givenTwoLists_whenUsingObjectStreams_thenArraysCombined() {
      assertThat(CombiningLists.usingJava8ObjectStream(first, second), is(expected));
   }

   @Test
   public void givenTwoLists_whenUsingFlatMaps_thenArraysCombined() {
      assertThat(CombiningLists.usingJava8FlatMaps(first, second), is(expected));
   }

   @Test
   public void givenTwoLists_whenUsingApacheCommons_thenArraysCombined() {
      assertThat(CombiningLists.usingApacheCommons(first, second), is(expected));
   }

   @Test
   public void givenTwoLists_whenUsingGuava_thenArraysCombined() {
      assertThat(CombiningLists.usingGuava(first, second), is(expected));
   }
}
