package cn.tuyucheng.taketoday.exceptions.illegalaccesserror;

public class IllegalAccessErrorExample {

   interface Tuyucheng {
      public default void foobar() {
         System.out.println("This is a default method.");
      }
   }

   class Super {
      private void foobar() {
         System.out.println("SuperClass method foobar");
      }
   }

   class MySubClass extends Super implements Tuyucheng {

   }
}