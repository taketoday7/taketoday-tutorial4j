package cn.tuyucheng.taketoday.exception.exceptions_vs_errors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Unit test for the RuntimeExceptionExample class.
public class RuntimeExceptionExampleUnitTest {
   @Test
   public void whenMainMethodIsRun_thenArrayIndexOutOfBoundsExceptionIsThrown() {
      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> RuntimeExceptionExample.main(null));
   }
}
