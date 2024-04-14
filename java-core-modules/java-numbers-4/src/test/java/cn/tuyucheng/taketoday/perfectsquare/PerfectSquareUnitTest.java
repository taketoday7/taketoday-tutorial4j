package cn.tuyucheng.taketoday.perfectsquare;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PerfectSquareUnitTest {

   @Test
   public void testIsNumberPerfectSquare() {
      long n = 18676209273604L; //‬ 18676209273604 = 43621598 * 43621598
      boolean expectedValue = true;
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingSqrt(n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingBinarySearch(1, Integer.MAX_VALUE, n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingNewtonMethod(n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareWithOptimization(n));

      n = 549790047707L; // prime number
      expectedValue = false;
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingSqrt(n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingBinarySearch(1, Integer.MAX_VALUE, n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareByUsingNewtonMethod(n));
      assertEquals(expectedValue, PerfectSquareUtil.isPerfectSquareWithOptimization(n));
   }


}
