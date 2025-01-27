package cn.tuyucheng.taketoday.collections.transformations

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FlattenUnitTest {
   @Test
   fun testFlatten() {
      val inputs = listOf("one", "two", "three")
      val characters = inputs.map(String::toList)
      val flattened = characters.flatten();
      assertEquals(listOf('o', 'n', 'e', 't', 'w', 'o', 't', 'h', 'r', 'e', 'e'), flattened)
   }

   @Test
   fun testFlatMap() {
      val inputs = listOf("one", "two", "three")
      val characters = inputs.flatMap(String::toList)
      assertEquals(listOf('o', 'n', 'e', 't', 'w', 'o', 't', 'h', 'r', 'e', 'e'), characters)
   }
}