package cn.tuyucheng.taketoday.stringtoboolean;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringToBooleanUnitTest {

   @Test
   public void givenStringTrue_whenUsingParseBoolean_thenTrue() {
      assertThat(Boolean.parseBoolean("true")).isTrue();
   }

   @Test
   public void givenStringTrue_whenUsingValueOf_thenTrue() {
      assertThat(Boolean.valueOf("true")).isTrue();
   }

   @Test
   public void givenStringTrue_whenUsingGetBoolean_thenFalse() {
      assertThat(Boolean.getBoolean("true")).isFalse();
   }

   @Test
   public void givenSystemProperty_whenUsingGetBoolean_thenTrue() {
      System.setProperty("CODING_IS_FUN", "true");

      assertThat(Boolean.getBoolean("CODING_IS_FUN")).isTrue();
   }
}
