package cn.tuyucheng.taketoday.fibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciSeriesUtilsUnitTest {

   @Test
   public void givenTermToCalculate_thenReturnThatTermUsingRecursion() {
      int term = 10;
      int expectedValue = 55;
      assertEquals(FibonacciSeriesUtils.nthFibonacciTermRecursiveMethod(term), expectedValue);
   }

   @Test
   public void givenTermToCalculate_thenReturnThatTermUsingIteration() {
      int term = 10;
      int expectedValue = 55;
      assertEquals(FibonacciSeriesUtils.nthFibonacciTermIterativeMethod(term), expectedValue);
   }

   @Test
   public void givenTermToCalculate_thenReturnThatTermUsingBinetsFormula() {
      int term = 10;
      int expectedValue = 55;
      assertEquals(FibonacciSeriesUtils.nthFibonacciTermUsingBinetsFormula(term), expectedValue);
   }
}
