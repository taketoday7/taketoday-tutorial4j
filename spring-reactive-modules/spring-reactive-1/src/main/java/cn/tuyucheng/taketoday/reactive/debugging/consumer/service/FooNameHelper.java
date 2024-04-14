package cn.tuyucheng.taketoday.reactive.debugging.consumer.service;

import cn.tuyucheng.taketoday.reactive.debugging.consumer.model.Foo;

import java.util.concurrent.ThreadLocalRandom;

public class FooNameHelper {

   public static Foo concatAndSubstringFooName(Foo foo) {
      Foo concat = concatFooName(foo);
      return substringFooName(concat);
   }

   public static Foo concatFooName(Foo foo) {
      int random = ThreadLocalRandom.current()
            .nextInt(0, 80);

      String processedName = (random != 0)
            ? foo.getFormattedName()
            : STR."\{foo.getFormattedName()}-bael";

      foo.setFormattedName(processedName);
      return foo;
   }

   public static Foo substringFooName(Foo foo) {
      int random = ThreadLocalRandom.current()
            .nextInt(0, 100);

      String processedName = (random == 0)
            ? foo.getFormattedName().substring(10, 15)
            : foo.getFormattedName().substring(0, 5);

      foo.setFormattedName(processedName);

      return foo;
   }
}