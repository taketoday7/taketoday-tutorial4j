package cn.tuyucheng.taketoday.nestedclass;

import org.junit.jupiter.api.Test;

abstract class SimpleAbstractClass {
   abstract void run();
}

public class AnonymousInnerUnitTest {

   @Test
   public void run() {
      SimpleAbstractClass simpleAbstractClass = new SimpleAbstractClass() {
         void run() {
            System.out.println("Running Anonymous Class...");
         }
      };
      simpleAbstractClass.run();
   }
}