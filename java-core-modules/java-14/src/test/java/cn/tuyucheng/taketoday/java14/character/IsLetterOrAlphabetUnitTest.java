package cn.tuyucheng.taketoday.java14.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsLetterOrAlphabetUnitTest {

   @Test
   public void givenACharacter_whenLetter_thenAssertIsLetterTrue() {
      assertTrue(Character.isLetter(65));
   }

   @Test
   public void givenACharacter_whenLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic(65));
   }

   @Test
   public void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsLetterFalse() {
      assertFalse(Character.isLetter(837));
   }

   @Test
   public void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic(837));
   }

}