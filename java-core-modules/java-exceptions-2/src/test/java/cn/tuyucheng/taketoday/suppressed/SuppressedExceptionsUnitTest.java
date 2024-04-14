package cn.tuyucheng.taketoday.suppressed;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuppressedExceptionsUnitTest {

   @Test
   public void givenNonExistentFileName_whenAttemptFileOpen_thenNullPointerException() throws IOException {
      assertThrows(NullPointerException.class, () -> SuppressedExceptionsDemo.demoSuppressedException("/non-existent-path/non-existent-file.txt"));
   }

   @Test
   public void givenNonExistentFileName_whenAttemptFileOpenStoreSuppressed_thenSuppressedExceptionAvailable() {
      try {
         SuppressedExceptionsDemo.demoAddSuppressedException("/non-existent-path/non-existent-file.txt");
      } catch (Exception e) {
         assertThat(e, instanceOf(NullPointerException.class));
         assertEquals(1, e.getSuppressed().length);
         assertThat(e.getSuppressed()[0], instanceOf(FileNotFoundException.class));
      }
   }

   @Test
   public void whenUsingExceptionalResource_thenSuppressedExceptionAvailable() {
      try {
         SuppressedExceptionsDemo.demoExceptionalResource();
      } catch (Exception e) {
         assertThat(e, instanceOf(IllegalArgumentException.class));
         assertEquals("Thrown from processSomething()", e.getMessage());
         assertEquals(1, e.getSuppressed().length);
         assertThat(e.getSuppressed()[0], instanceOf(NullPointerException.class));
         assertEquals("Thrown from close()", e.getSuppressed()[0].getMessage());
      }
   }
}
