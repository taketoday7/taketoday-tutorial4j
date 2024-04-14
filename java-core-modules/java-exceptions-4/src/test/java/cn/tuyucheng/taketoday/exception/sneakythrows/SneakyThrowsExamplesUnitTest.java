package cn.tuyucheng.taketoday.exception.sneakythrows;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static cn.tuyucheng.taketoday.exception.sneakythrows.SneakyThrowsExamples.throwSneakyIOException;
import static cn.tuyucheng.taketoday.exception.sneakythrows.SneakyThrowsExamples.throwSneakyIOExceptionUsingLombok;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SneakyThrowsExamplesUnitTest {

   @Test
   public void throwSneakyIOException_IOExceptionShouldBeThrown() {
      assertThatThrownBy(() -> throwSneakyIOException())
            .isInstanceOf(IOException.class)
            .hasMessage("sneaky")
            .hasStackTraceContaining("SneakyThrowsExamples.throwSneakyIOException");
   }

   @Test
   public void throwSneakyIOExceptionUsingLombok_IOExceptionShouldBeThrown() {
      assertThatThrownBy(() -> throwSneakyIOExceptionUsingLombok())
            .isInstanceOf(IOException.class)
            .hasMessage("lombok sneaky")
            .hasStackTraceContaining("SneakyThrowsExamples.throwSneakyIOExceptionUsingLombok");
   }
}
