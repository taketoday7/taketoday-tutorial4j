package cn.tuyucheng.taketoday.utilities.lombok;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsWithNoArgsConstructorUnitTest {

   @Test
   void givenAnEmptyString_whenCallingIsEmpty_thenResultIsTrue() {
      Assertions.assertThat(StringUtilsWithNoArgsConstructor.isEmpty("")).isTrue();
   }

   @Test
   void givenNonEmptyString_whenCallingIsEmpty_thenResultIsFalse() {
      assertThat(StringUtilsWithNoArgsConstructor.isEmpty("asd")).isFalse();
   }

   @Test
   void givenAnEmptyString_whenCallingWrap_thenResultIsAnEmptyString() {
      assertThat(StringUtilsWithNoArgsConstructor.wrap("", "wrapper")).isEmpty();
   }

   @Test
   void givenNonEmptyString_whenCallingWrap_thenResultIsWrappedString() {
      assertThat(StringUtilsWithNoArgsConstructor.wrap("asd", "wrapper")).isEqualTo("wrapperasdwrapper");
   }

}
