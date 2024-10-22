package cn.tuyucheng.taketoday.rangeiterator

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class CustomColorUnitTest {

   @Test
   fun testInvalidConstructor() {
      assertFailsWith(IllegalStateException::class) {
         CustomColor(-1)
      }
   }

   @Test
   fun assertHas10Colors() {
      assertTrue {
         val a = CustomColor(1)
         val b = CustomColor(10)
         val range = a..b
         for (cc in range) {
            println(cc)
         }
         range.toList().size == 10
      }
   }

   @Test
   fun assertContains0xCCCCCC() {
      assertTrue {
         val a = CustomColor(0xBBBBBB)
         val b = CustomColor(0xDDDDDD)
         val range = a..b
         range.contains(CustomColor(0xCCCCCC))
      }
   }

}

