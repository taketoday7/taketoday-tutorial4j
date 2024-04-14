package cn.tuyucheng.taketoday.exceptions.classcastexception;

import org.junit.jupiter.api.Test;

public class GenericConversionUnitTest {

   @Test(expected = ClassCastException.class)
   public void givenIncompatibleType_whenConvertInstanceOfObject_thenClassCastException() {
      // Should have been null, but due to type erasure, inside convertInstanceOfObject,
      // it will attempt to cast to Object instead of String, so it casts to Object, which is always possible.
      String shouldBeNull = convertInstanceOfObject(123);
   }

   public static <T> T convertInstanceOfObject(Object o) {
      try {
         return (T) o; // Casts to Object due to type erasure
      } catch (ClassCastException e) {
         return null; // Will never reach this
      }
   }
}
