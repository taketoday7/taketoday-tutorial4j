package cn.tuyucheng.taketoday.replacechar;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReplaceCharInStringUnitTest {
   private ReplaceCharacterInString characterInString = new ReplaceCharacterInString();

   @Test
   public void whenReplaceCharAtIndexUsingSubstring_thenSuccess() {
      assertEquals("abcme", characterInString.replaceCharSubstring("abcde", 'm', 3));
   }

   @Test
   public void whenReplaceCharAtIndexUsingCharArray_thenSuccess() {
      assertEquals("abcme", characterInString.replaceCharUsingCharArray("abcde", 'm', 3));
   }

   @Test
   public void whenReplaceCharAtIndexUsingStringBuilder_thenSuccess() {
      assertEquals("abcme", characterInString.replaceCharStringBuilder("abcde", 'm', 3));
   }

   @Test
   public void whenReplaceCharAtIndexUsingStringBuffer_thenSuccess() {
      assertEquals("abcme", characterInString.replaceCharStringBuffer("abcde", 'm', 3));
   }
}
