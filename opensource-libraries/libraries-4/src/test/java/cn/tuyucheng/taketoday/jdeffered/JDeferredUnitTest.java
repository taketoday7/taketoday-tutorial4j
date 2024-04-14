package cn.tuyucheng.taketoday.jdeffered;

import cn.tuyucheng.taketoday.jdeffered.PipeDemo.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JDeferredUnitTest {

   @Test
   public void givenJob_expectPromise() {
      PromiseDemo.startJob("Tuyucheng Job");
   }

   @Test
   public void givenMsg_expectModifiedMsg() {
      String msg = FilterDemo.filter("Tuyucheng");
      assertEquals("Hello Tuyucheng", msg);
   }

   @Test
   public void givenNum_validateNum_expectStatus() {
      Result result = PipeDemo.validate(80);
      assertEquals(result, Result.SUCCESS);
   }
}
