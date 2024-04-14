package cn.tuyucheng.taketoday.firstchardigit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstCharDigitUnitTest {

   @Test
   void givenString_whenUsingCharAtMethod_thenSuccess() {
      Assertions.assertTrue(FirstCharDigit.checkUsingCharAtMethod("12 years"));
      assertFalse(FirstCharDigit.checkUsingCharAtMethod("years"));
      assertFalse(FirstCharDigit.checkUsingCharAtMethod(""));
      assertFalse(FirstCharDigit.checkUsingCharAtMethod(null));
   }

   @Test
   void givenString_whenUsingIsDigitMethod_thenSuccess() {
      assertTrue(FirstCharDigit.checkUsingIsDigitMethod("10 cm"));
      assertFalse(FirstCharDigit.checkUsingIsDigitMethod("cm"));
      assertFalse(FirstCharDigit.checkUsingIsDigitMethod(""));
      assertFalse(FirstCharDigit.checkUsingIsDigitMethod(null));
   }

   @Test
   void givenString_whenUsingPatternClass_thenSuccess() {
      assertTrue(FirstCharDigit.checkUsingPatternClass("1 kg"));
      assertFalse(FirstCharDigit.checkUsingPatternClass("kg"));
      assertFalse(FirstCharDigit.checkUsingPatternClass(""));
      assertFalse(FirstCharDigit.checkUsingPatternClass(null));
   }

   @Test
   void givenString_whenUsingMatchesMethod_thenSuccess() {
      assertTrue(FirstCharDigit.checkUsingMatchesMethod("123"));
      assertFalse(FirstCharDigit.checkUsingMatchesMethod("ABC"));
      assertFalse(FirstCharDigit.checkUsingMatchesMethod(""));
      assertFalse(FirstCharDigit.checkUsingMatchesMethod(null));
   }

   @Test
   void givenString_whenUsingCharMatcherInRangeMethod_thenSuccess() {
      assertTrue(FirstCharDigit.checkUsingCharMatcherInRangeMethod("2023"));
      assertFalse(FirstCharDigit.checkUsingCharMatcherInRangeMethod("abc"));
      assertFalse(FirstCharDigit.checkUsingCharMatcherInRangeMethod(""));
      assertFalse(FirstCharDigit.checkUsingCharMatcherInRangeMethod(null));
   }

   @Test
   void givenString_whenUsingCharMatcherForPredicateMethod_thenSuccess() {
      assertTrue(FirstCharDigit.checkUsingCharMatcherForPredicateMethod("100"));
      assertFalse(FirstCharDigit.checkUsingCharMatcherForPredicateMethod("abdo"));
      assertFalse(FirstCharDigit.checkUsingCharMatcherForPredicateMethod(""));
      assertFalse(FirstCharDigit.checkUsingCharMatcherForPredicateMethod(null));
   }

}
