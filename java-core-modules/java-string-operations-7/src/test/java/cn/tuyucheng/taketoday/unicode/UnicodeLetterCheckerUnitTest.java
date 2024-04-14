package cn.tuyucheng.taketoday.unicode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnicodeLetterCheckerUnitTest {
   @Test
   public void givenString_whenUsingIsLetter_thenReturnTrue() {
      UnicodeLetterChecker checker = new UnicodeLetterChecker();

      boolean isUnicodeLetter = checker.characterClassCheck("HelloWorld");
      assertTrue(isUnicodeLetter);
   }

   @Test
   public void givenString_whenUsingRegex_thenReturnTrue() {
      UnicodeLetterChecker checker = new UnicodeLetterChecker();

      boolean isUnicodeLetter = checker.regexCheck("HelloWorld");
      assertTrue(isUnicodeLetter);
   }

   @Test
   public void givenString_whenUsingIsAlpha_thenReturnTrue() {
      UnicodeLetterChecker checker = new UnicodeLetterChecker();

      boolean isUnicodeLetter = checker.isAlphaCheck("HelloWorld");
      assertTrue(isUnicodeLetter);
   }

   @Test
   public void givenString_whenUsingStreams_thenReturnTrue() {
      UnicodeLetterChecker checker = new UnicodeLetterChecker();

      boolean isUnicodeLetter = checker.StreamsCheck("HelloWorld");
      assertTrue(isUnicodeLetter);
   }
}
