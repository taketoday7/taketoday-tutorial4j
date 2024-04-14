package cn.tuyucheng.taketoday.exception.currentstacktrace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentStacktraceDemoUnitTest {

   @Test
   public void whenElementIsFecthedUsingThread_thenCorrectMethodAndClassIsReturned() {
      StackTraceElement[] stackTrace = new StackTraceUsingThreadDemo().methodA();

      StackTraceElement elementZero = stackTrace[0];
      assertEquals("java.lang.Thread", elementZero.getClassName());
      assertEquals("getStackTrace", elementZero.getMethodName());

      StackTraceElement elementOne = stackTrace[1];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.StackTraceUsingThreadDemo", elementOne.getClassName());
      assertEquals("methodB", elementOne.getMethodName());

      StackTraceElement elementTwo = stackTrace[2];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.StackTraceUsingThreadDemo", elementTwo.getClassName());
      assertEquals("methodA", elementTwo.getMethodName());

      StackTraceElement elementThree = stackTrace[3];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.CurrentStacktraceDemoUnitTest", elementThree.getClassName());
      assertEquals("whenElementIsFecthedUsingThread_thenCorrectMethodAndClassIsReturned", elementThree.getMethodName());
   }

   @Test
   public void whenElementIsFecthedUsingThrowable_thenCorrectMethodAndClassIsReturned() {
      StackTraceElement[] stackTrace = new StackTraceUsingThrowableDemo().methodA();

      StackTraceElement elementZero = stackTrace[0];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.StackTraceUsingThrowableDemo", elementZero.getClassName());
      assertEquals("methodB", elementZero.getMethodName());

      StackTraceElement elementOne = stackTrace[1];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.StackTraceUsingThrowableDemo", elementOne.getClassName());
      assertEquals("methodA", elementOne.getMethodName());

      StackTraceElement elementThree = stackTrace[2];
      assertEquals("cn.tuyucheng.taketoday.exception.currentstacktrace.CurrentStacktraceDemoUnitTest", elementThree.getClassName());
      assertEquals("whenElementIsFecthedUsingThrowable_thenCorrectMethodAndClassIsReturned", elementThree.getMethodName());
   }
}
