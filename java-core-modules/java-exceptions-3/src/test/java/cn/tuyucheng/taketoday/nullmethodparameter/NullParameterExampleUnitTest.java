package cn.tuyucheng.taketoday.nullmethodparameter;

import org.junit.jupiter.api.Test;

public class NullParameterExampleUnitTest {
   @Test(expected = IllegalArgumentException.class)
   public void givenNullParameter_whenProcessSomethingNotNull_thenIllegalArgumentException() {
      NullParameterExample example = new NullParameterExample();
      example.processSomethingNotNull(null);
   }

   @Test(expected = NullPointerException.class)
   public void givenNullParameter_whenProcessSomethingElseNotNull_thenNullPointerException() {
      NullParameterExample example = new NullParameterExample();
      example.processSomethingElseNotNull(null);
   }
}
