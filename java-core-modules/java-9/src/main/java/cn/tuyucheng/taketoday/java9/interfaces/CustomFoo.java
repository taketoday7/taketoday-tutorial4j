package cn.tuyucheng.taketoday.java9.interfaces;

public class CustomFoo implements Foo {

   public static void main(String... args) {
      Foo customFoo = new CustomFoo();
      customFoo.bar(); // 'Hello world!'
      buzz(); // 'Hello static world!'
   }
}
