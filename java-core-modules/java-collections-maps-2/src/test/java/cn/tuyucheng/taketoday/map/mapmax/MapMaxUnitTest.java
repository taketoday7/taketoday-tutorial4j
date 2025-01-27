package cn.tuyucheng.taketoday.map.mapmax;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapMaxUnitTest {

   Map<Integer, Integer> map = null;
   MapMax mapMax = null;


   @BeforeEach
   public void setupTestData() {
      map = new HashMap<Integer, Integer>();
      map.put(23, 12);
      map.put(46, 24);
      map.put(27, 38);
      mapMax = new MapMax();
   }

   @Test
   public void givenMap_whenIterated_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingIteration(map));
   }

   @Test
   public void givenMap_whenUsingCollectionsMax_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingCollectionsMax(map));
   }

   @Test
   public void givenMap_whenUsingCollectionsMaxAndLambda_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingCollectionsMaxAndLambda(map));
   }

   @Test
   public void givenMap_whenUsingCollectionsMaxAndMethodReference_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingCollectionsMaxAndMethodReference(map));
   }

   @Test
   public void givenMap_whenUsingStreamAndLambda_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingStreamAndLambda(map));
   }

   @Test
   public void givenMap_whenUsingStreamAndMethodReference_thenReturnMaxValue() {
      assertEquals(new Integer(38), mapMax.maxUsingStreamAndMethodReference(map));
   }


}
