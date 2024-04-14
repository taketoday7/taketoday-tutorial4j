package cn.tuyucheng.taketoday.nestedclass;

import org.junit.jupiter.api.Test;

public class NewEnclosing {

   private void run() {
      class Local {
         void run() {
            System.out.println("Welcome to Tuyucheng!");
         }
      }
      Local local = new Local();
      local.run();
   }

   @Test
   public void test() {
      NewEnclosing newEnclosing = new NewEnclosing();
      newEnclosing.run();
   }
}