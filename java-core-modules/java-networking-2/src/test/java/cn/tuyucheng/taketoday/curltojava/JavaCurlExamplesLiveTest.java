package cn.tuyucheng.taketoday.curltojava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JavaCurlExamplesLiveTest {

   @Test
   public void givenCommand_whenCalled_thenProduceZeroExitCode() throws IOException {
      String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
      ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
      processBuilder.directory(new File("/home/"));
      Process process = processBuilder.start();
      InputStream inputStream = process.getInputStream();
      // Consume the inputStream so the process can exit
      JavaCurlExamples.consumeInputStream(inputStream);
      int exitCode = process.exitValue();

      Assertions.assertEquals(0, exitCode);
   }

   @Test
   public void givenNewCommands_whenCalled_thenCheckIfIsAlive() throws IOException {
      String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
      ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
      processBuilder.directory(new File("/home/"));
      Process process = processBuilder.start();

      // Re-use processBuilder
      processBuilder.command(new String[]{"newCommand", "arguments"});

      Assertions.assertEquals(true, process.isAlive());
   }

   @Test
   public void whenRequestPost_thenCheckIfReturnContent() throws IOException {
      String command = "curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2";
      Process process = Runtime.getRuntime().exec(command);

      // Get the POST result
      String content = JavaCurlExamples.inputStreamToString(process.getInputStream());

      Assertions.assertTrue(null != content && !content.isEmpty());
   }

}
