package cn.tuyucheng.taketoday.concurrent.countdownlatch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountdownLatchCountExampleUnitTest {

   @Test
   public void whenCountDownLatch_completed() {
      CountdownLatchCountExample ex = new CountdownLatchCountExample(2);
      boolean isCompleted = ex.callTwiceInSameThread();
      assertTrue(isCompleted);
   }
}
