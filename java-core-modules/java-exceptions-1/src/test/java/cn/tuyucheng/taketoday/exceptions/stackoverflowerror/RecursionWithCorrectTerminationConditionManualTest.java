package cn.tuyucheng.taketoday.exceptions.stackoverflowerror;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

public class RecursionWithCorrectTerminationConditionManualTest {
   @Test
   public void givenNegativeInt_whenCalcFact_thenCorrectlyCalc() {
      int numToCalcFactorial = -1;
      RecursionWithCorrectTerminationCondition rctc = new RecursionWithCorrectTerminationCondition();

      assertEquals(1, rctc.calculateFactorial(numToCalcFactorial));
   }
}
