package cn.tuyucheng.taketoday.tinylog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

class TinylogIntegrationTest {

   private ByteArrayOutputStream consoleOutput = new ByteArrayOutputStream();

   @BeforeEach
   void init() throws UnsupportedEncodingException {
      System.setOut(new PrintStream(consoleOutput, false, "UTF-8"));
   }

   @Test
   void whenLoggingStaticText_thenOutputIt() throws UnsupportedEncodingException {
      Logger.info("Hello World!");

      String outputLog = consoleOutput.toString("UTF-8");
      assertThat(outputLog).isEqualToIgnoringNewLines("Hello World!");
   }

   @Test
   void whenLoggingParamizedText_thenOutputItResolved() throws UnsupportedEncodingException {
      Logger.info("Hello {}!", "Alice");

      String outputLog = consoleOutput.toString("UTF-8");
      assertThat(outputLog).isEqualToIgnoringNewLines("Hello Alice!");
   }

   @Test
   void whenLoggingNumberWithFormatPattern_thenOutputItFormatted() throws UnsupportedEncodingException {
      Logger.info("π = {0.00}", Math.PI);

      String outputLog = consoleOutput.toString("UTF-8");
      assertThat(outputLog).isEqualToIgnoringNewLines("π = 3.14");
   }

   @Test
   void whenLoggingExceptionWithMessage_thenOutputMessageAndException() throws UnsupportedEncodingException {
      int a = 42;
      int b = 0;
      try {
         int i = a / b;
      } catch (Exception ex) {
         Logger.error(ex, "Cannot divide {} by {}", a, b);
      }

      String outputLog = consoleOutput.toString("UTF-8");
      assertThat(outputLog).startsWith("Cannot divide 42 by 0: java.lang.ArithmeticException");
   }

   @Test
   void whenLoggingExceptionWithoutMessage_thenOutputExceptionOnly() throws UnsupportedEncodingException {
      try {
         int i = 42 / 0;
      } catch (Exception ex) {
         Logger.error(ex);
      }

      String outputLog = consoleOutput.toString("UTF-8");
      assertThat(outputLog).startsWith("java.lang.ArithmeticException");
   }
}