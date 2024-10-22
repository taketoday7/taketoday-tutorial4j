package cn.tuyucheng.taketoday.stopexecution;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import java.net.MalformedURLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StopExecutionFurtherCodeUnitTest {


   @Test
   void givenExecution_whenStopIsNotCalled_thenTaskIsPerformed() {
      StopExecutionFurtherCode stopExecution = new StopExecutionFurtherCode();
      int performedTask = stopExecution.performTask(10, 20);
      Assertions.assertEquals(30, performedTask);
   }

   // This test case have been commented because, otherwise, the program will exit since System.exit(statusCode) is being used.
    /*@Test
    void givenExecution_whenStopIsCalled_thenTaskNotPerformed() {
        StopExecutionFurtherCode stopExecution = new StopExecutionFurtherCode();
        stopExecution.stop();
        int performedTask = stopExecution.performTask(10, 20);
        Assert.assertEquals(30, performedTask);
    }*/

   @Test
   void givenWrongUrlAndPath_whenDownloadCalled_thenExecutionIsStopped() throws MalformedURLException {
      StopExecutionFurtherCode stopExecutionFurtherCode = new StopExecutionFurtherCode();
      stopExecutionFurtherCode.download("", "");
   }

   @Test
   void givenName_whenStopExecutionUsingExceptionCalled_thenNameIsConvertedToUpper() {
      StopExecutionFurtherCode stopExecutionFurtherCode = new StopExecutionFurtherCode();
      String name = "John";
      String result1 = stopExecutionFurtherCode.stopExecutionUsingException(name);
      Assertions.assertEquals("JOHN", result1);
      try {
         Integer number1 = 10;
         Assertions.assertThrows(IllegalArgumentException.class, () -> {
            int result = stopExecutionFurtherCode.stopExecutionUsingException(number1);
         });
      } catch (Exception e) {
         Assertions.fail("Unexpected exception thrown: " + e.getMessage());
      }
   }

   @Test
   void givenBaseCase_whenStopExecutionWhenBaseCaseKnownCalled_thenFactorialIsCalculated() throws MalformedURLException {
      StopExecutionFurtherCode stopExecutionFurtherCode = new StopExecutionFurtherCode();
      int factorial = stopExecutionFurtherCode.calculateFactorial(1);
      Assertions.assertEquals(1, factorial);
   }

   @Test
   void givenArrayWithNegative_whenStopExecutionInLoopCalled_thenSumIsCalculatedIgnoringNegatives() {
      StopExecutionFurtherCode stopExecutionFurtherCode = new StopExecutionFurtherCode();
      int[] nums = {1, 2, 3, -1, 1, 2, 3};
      int sum = stopExecutionFurtherCode.calculateSum(nums);
      Assertions.assertEquals(6, sum);
   }

   @Test
   void givenThreadRunning_whenInterrupted_thenThreadExecutionIsStopped() throws InterruptedException {
      InterruptThread stopExecution = new InterruptThread();
      stopExecution.start();
      Thread.sleep(2000);
      stopExecution.interrupt();
      stopExecution.join();
      Assertions.assertTrue(!stopExecution.isAlive());
   }

   @Test
   void givenLinesWithStopLabel_whenStopExecutionLabeledLoopCalled_thenLoopExecutionIsStopped() {
      StopExecutionFurtherCode furtherCode = new StopExecutionFurtherCode();
      final String[] lines = {"Line 1", "Line 2", "Line 3", "stop", "Line 4", "Line 5"};
      int statusCode = furtherCode.processLines(lines);
      Assertions.assertEquals(-1, statusCode);
   }

}
