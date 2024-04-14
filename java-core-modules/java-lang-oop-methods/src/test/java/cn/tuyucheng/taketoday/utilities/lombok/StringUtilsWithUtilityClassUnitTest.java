package cn.tuyucheng.taketoday.utilities.lombok;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsWithUtilityClassUnitTest {

   @Test
   void givenAnEmptyString_whenCallingIsEmpty_thenResultIsTrue() {
      Assertions.assertThat(StringUtilsWithUtilityClass.isEmpty("")).isTrue();
   }

   @Test
   void givenNonEmptyString_whenCallingIsEmpty_thenResultIsFalse() {
      assertThat(StringUtilsWithUtilityClass.isEmpty("asd")).isFalse();
   }

   @Test
   void givenAnEmptyString_whenCallingWrap_thenResultIsAnEmptyString() {
      assertThat(StringUtilsWithUtilityClass.wrap("", "wrapper")).isEmpty();
   }

   @Test
   void givenNonEmptyString_whenCallingWrap_thenResultIsWrappedString() {
      assertThat(StringUtilsWithUtilityClass.wrap("asd", "wrapper")).isEqualTo("wrapperasdwrapper");
   }

}
