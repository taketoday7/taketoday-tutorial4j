package cn.tuyucheng.taketoday.array.removefirst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class RemoveFirstElementUnitTest {

   private List<String> list = new ArrayList<>();
   private LinkedList<String> linkedList = new LinkedList<>();

   @BeforeEach
   public void init() {

      list.add("cat");
      list.add("dog");
      list.add("pig");
      list.add("cow");
      list.add("goat");

      linkedList.add("cat");
      linkedList.add("dog");
      linkedList.add("pig");
      linkedList.add("cow");
      linkedList.add("goat");
   }

   @Test
   public void givenList_whenRemoveFirst_thenRemoved() {
      list.remove(0);

      assertThat(list, hasSize(4));
      assertThat(list, not(contains("cat")));
   }

   @Test
   public void givenLinkedList_whenRemoveFirst_thenRemoved() {

      linkedList.removeFirst();

      assertThat(linkedList, hasSize(4));
      assertThat(linkedList, not(contains("cat")));
   }

   @Test
   public void givenStringArray_whenRemovingFirstElement_thenArrayIsSmallerAndElementRemoved() {
      String[] stringArray = {"foo", "bar", "baz"};

      String[] modifiedArray = Arrays.copyOfRange(stringArray, 1, stringArray.length);

      assertThat(modifiedArray.length, is(2));
      assertThat(modifiedArray[0], is("bar"));
   }

}
