package cn.tuyucheng.taketoday.staticmethods;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomStringUtilsUnitTest {

   @Test
   void givenNonEmptyString_whenIsEmptyMethodIsCalled_thenFalseIsReturned() {
      boolean empty = CustomStringUtils.isEmpty("tuyucheng");
      assertThat(empty).isFalse();
   }

   @Test
   void givenEmptyString_whenIsEmptyMethodIsCalled_thenTrueIsReturned() {
      boolean empty = CustomStringUtils.isEmpty("");
      assertThat(empty).isTrue();
   }

}
