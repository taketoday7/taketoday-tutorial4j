package cn.tuyucheng.taketoday.algorithms.percentage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PercentageCalculatorUnitTest {
   private PercentageCalculator pc = new PercentageCalculator();

   @Test
   public void whenPass2Integers_thenShouldCalculatePercentage() {
      Assertions.assertEquals("Result not as expected",
            50.0, pc.calculatePercentage(50, 100), 0.1);
   }

   @Test
   public void whenPassObtainedMarksAsDouble_thenShouldCalculatePercentage() {
      Assertions.assertEquals("Result not as expected", 5.05,
            pc.calculatePercentage(50.5, 1000), 0.1);
   }

   @Test
   public void whenPassTotalMarksAsDouble_thenShouldCalculatePercentage() {
      Assertions.assertEquals("Result not as expected", 19.6,
            pc.calculatePercentage(5, 25.5), 0.1);
   }

   @Test
   public void whenPass2DoubleNumbers_thenShouldCalculatePercentage() {
      Assertions.assertEquals("Result not as expected", 20,
            pc.calculatePercentage(5.5, 27.5), 0.1);
   }

}
