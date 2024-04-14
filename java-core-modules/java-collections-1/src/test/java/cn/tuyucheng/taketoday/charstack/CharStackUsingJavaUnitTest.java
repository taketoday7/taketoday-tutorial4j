package cn.tuyucheng.taketoday.charstack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharStackUsingJavaUnitTest {

   @Test
   public void whenCharStackIsCreated_thenItHasSize0() {

      Stack<Character> charStack = new Stack<>();

      assertEquals(0, charStack.size());
   }

   @Test
   public void givenEmptyCharStack_whenElementIsPushed_thenStackSizeisIncreased() {

      Stack<Character> charStack = new Stack<>();

      charStack.push('A');

      assertEquals(1, charStack.size());
   }

   @Test
   public void givenCharStack_whenElementIsPoppedFromStack_thenElementIsRemovedAndSizeChanges() {

      Stack<Character> charStack = new Stack<>();
      charStack.push('A');

      char element = charStack.pop();

      assertEquals('A', element);
      assertEquals(0, charStack.size());
   }

   @Test
   public void givenCharStack_whenElementIsPeeked_thenElementIsNotRemovedAndSizeDoesNotChange() {

      Stack<Character> charStack = new Stack<>();
      charStack.push('A');

      char element = charStack.peek();

      assertEquals('A', element);
      assertEquals(1, charStack.size());
   }

}
