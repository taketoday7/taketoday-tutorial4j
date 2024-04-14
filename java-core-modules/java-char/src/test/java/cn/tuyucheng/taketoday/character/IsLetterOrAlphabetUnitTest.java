package cn.tuyucheng.taketoday.character;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsLetterOrAlphabetUnitTest {

   @Test
   public void givenACharacter_whenUpperCaseLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic('A'));
   }

   @Test
   public void givenACharacter_whenTitleCaseLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic('\u01f2'));
   }

   @Test
   public void givenACharacter_whenLowerCaseLetter_thenAssertIsLetterTrue() {
      assertTrue(Character.isAlphabetic('a'));
   }

   @Test
   public void givenACharacter_whenModifierLetter_thenAssertIsLetterTrue() {
      assertTrue(Character.isAlphabetic('\u02b0'));
   }

   @Test
   public void givenACharacter_whenLetter_thenAssertIsLetterTrue() {
      assertTrue(Character.isLetter('a'));
   }

   @Test
   public void givenACharacter_whenLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic('a'));
   }

   @Test
   public void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsLetterFalse() {
      assertFalse(Character.isLetter('\u2164'));
   }

   @Test
   public void givenACharacter_whenAlphabeticAndNotLetter_thenAssertIsAlphabeticTrue() {
      assertTrue(Character.isAlphabetic('\u2164'));
   }
}
