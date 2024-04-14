package cn.tuyucheng.taketoday.list.flattennestedlist;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlattenNestedListUnitTest {
   private List<List<String>> lol = asList(asList("one:one"), asList("two:one", "two:two", "two:three"), asList("three:one", "three:two", "three:three", "three:four"));

   @Test
   public void givenNestedList_thenFlattenImperatively() {
      List<String> ls = flattenListOfListsImperatively(lol);

      assertNotNull(ls);
      assertTrue(ls.size() == 8);
      // assert content
      assertThat(ls, IsIterableContainingInOrder.contains("one:one", "two:one", "two:two", "two:three", "three:one", "three:two", "three:three", "three:four"));
   }

   @Test
   public void givenNestedList_thenFlattenFunctionally() {
      List<String> ls = flattenListOfListsStream(lol);

      assertNotNull(ls);
      assertTrue(ls.size() == 8);
      // assert content
      assertThat(ls, IsIterableContainingInOrder.contains("one:one", "two:one", "two:two", "two:three", "three:one", "three:two", "three:three", "three:four"));
   }

   private <T> List<T> flattenListOfListsImperatively(List<List<T>> list) {
      List<T> ls = new ArrayList<>();
      list.forEach(ls::addAll);
      return ls;
   }

   private <T> List<T> flattenListOfListsStream(List<List<T>> list) {
      return list.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
   }
}
