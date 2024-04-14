package cn.tuyucheng.taketoday.utilities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsUnitTest {

   @Test
   void givenAnEmptyString_whenCallingIsEmpty_thenResultIsTrue() {
      Assertions.assertThat(StringUtils.isEmpty("")).isTrue();
   }

   @Test
   void givenNonEmptyString_whenCallingIsEmpty_thenResultIsFalse() {
      assertThat(StringUtils.isEmpty("asd")).isFalse();
   }

   @Test
   void givenAnEmptyString_whenCallingWrap_thenResultIsAnEmptyString() {
      assertThat(StringUtils.wrap("", "wrapper")).isEmpty();
   }

   @Test
   void givenNonEmptyString_whenCallingWrap_thenResultIsWrappedString() {
      assertThat(StringUtils.wrap("asd", "wrapper")).isEqualTo("wrapperasdwrapper");
   }

}
