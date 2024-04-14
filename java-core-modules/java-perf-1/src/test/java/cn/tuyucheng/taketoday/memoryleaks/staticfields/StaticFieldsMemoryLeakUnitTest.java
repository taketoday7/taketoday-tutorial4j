package cn.tuyucheng.taketoday.memoryleaks.staticfields;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StaticFieldsMemoryLeakUnitTest {
   public static List<Double> list = new ArrayList<>();

   public void populateList() {
      for (int i = 0; i < 10000000; i++) {
         list.add(Math.random());
      }
      System.out.println("Debug Point 2");
   }

   @Test
   @Disabled // Test deliberately ignored as memory leak tests consume lots of resources
   public void givenStaticLargeList_whenPopulatingList_thenListIsNotGarbageCollected() {
      System.out.println("Debug Point 1");
      new StaticFieldsDemo().populateList();
      System.out.println("Debug Point 3");
   }
}
