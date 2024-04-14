package cn.tuyucheng.taketoday.trifunction;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriFunctionExampleUnitTest {

   private static final String BAELDUNG = "tuyucheng";

   @Test
   void whenMultiplyThenAdd_ThenReturnsCorrectResult() {
      assertEquals(25, TriFunctionExample.multiplyThenAdd.apply(2, 10, 5));
   }

   @Test
   void whenMultiplyThenAddThenDivideByTen_ThenReturnsCorrectResult() {
      assertEquals(2, TriFunctionExample.multiplyThenAddThenDivideByTen.apply(2, 10, 5));
   }

   @Test
   void givenTrueBooleanAndNullInteger_WhenConvertIntegerOrReturnStringDependingOnCondition_ThenEmptyString() {
      assertEquals("", TriFunctionExample.convertIntegerOrReturnStringDependingOnCondition.apply(null, BAELDUNG, true));
   }

   @Test
   void givenTrueBooleanAndNonNullInteger_WhenConvertIntegerOrReturnStringDependingOnCondition_ThenConvertIntegerToString() {
      assertEquals("88", TriFunctionExample.convertIntegerOrReturnStringDependingOnCondition.apply(88, BAELDUNG, true));
   }

   @Test
   void givenFalseBoolean_WhenConvertIntegerOrReturnStringDependingOnCondition_ThenReturnTheString() {
      assertEquals(BAELDUNG, TriFunctionExample.convertIntegerOrReturnStringDependingOnCondition.apply(null, BAELDUNG, false));
   }

   @Test
   void givenNullBoolean_WhenConvertIntegerOrReturnStringDependingOnCondition_ThenReturnTheString() {
      assertEquals(BAELDUNG, TriFunctionExample.convertIntegerOrReturnStringDependingOnCondition.apply(null, BAELDUNG, null));
   }

}
