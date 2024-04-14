package cn.tuyucheng.taketoday.nestedclass;

import org.junit.jupiter.api.Test;

public class Outer {

   public class Inner {

      public void run() {
         System.out.println("Calling test...");
      }
   }

   @Test
   public void test() {
      Outer outer = new Outer();
      Inner inner = outer.new Inner();
      inner.run();
   }
}
