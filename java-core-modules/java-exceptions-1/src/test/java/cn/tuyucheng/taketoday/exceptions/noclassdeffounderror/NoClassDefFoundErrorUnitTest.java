package cn.tuyucheng.taketoday.exceptions.noclassdeffounderror;

import org.junit.jupiter.api.Test;

public class NoClassDefFoundErrorUnitTest {

   @Test(expected = NoClassDefFoundError.class)
   public void givenInitErrorInClass_whenloadClass_thenNoClassDefFoundError() {
      NoClassDefFoundErrorExample sample = new NoClassDefFoundErrorExample();
      sample.getClassWithInitErrors();
   }
}