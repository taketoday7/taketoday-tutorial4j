package cn.tuyucheng.taketoday.utilities.alternatives;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsInterfaceUnitTest {

   @Test
   void givenAnEmptyString_whenCallingIsEmpty_thenResultIsTrue() {
      Assertions.assertThat(StringUtilsInterface.isEmpty("")).isTrue();
   }

   @Test
   void givenNonEmptyString_whenCallingIsEmpty_thenResultIsFalse() {
      assertThat(StringUtilsInterface.isEmpty("asd")).isFalse();
   }

   @Test
   void givenAnEmptyString_whenCallingWrap_thenResultIsAnEmptyString() {
      assertThat(StringUtilsInterface.wrap("", "wrapper")).isEmpty();
   }

   @Test
   void givenNonEmptyString_whenCallingWrap_thenResultIsWrappedString() {
      assertThat(StringUtilsInterface.wrap("asd", "wrapper")).isEqualTo("wrapperasdwrapper");
   }

}
