package cn.tuyucheng.taketoday.foldvsreduce

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class FoldAndReduceUnitTest {

   @Test
   fun testReduceLimitations() {
      val numbers: List<Int> = listOf(1, 2, 3)
      val sum: Number = numbers.reduce { acc, next -> acc + next }
      assertEquals(6, sum)

      val emptyList = listOf<Int>()
      assertThrows<RuntimeException> { emptyList.reduce { acc, next -> acc + next } }

      // doesn't compile
      // val sum = numbers.reduce { acc, next -> acc.toLong() + next.toLong()}
   }

   @Test
   fun testFold() {

      val numbers: List<Int> = listOf(1, 2, 3)
      val sum: Int = numbers.fold(0) { acc, next -> acc + next }
      assertEquals(6, sum)

      //change result type
      val sumLong: Long = numbers.fold(0L) { acc, next -> acc + next.toLong() }
      assertEquals(6L, sumLong)

      val emptyList = listOf<Int>()
      val emptySum = emptyList.fold(0) { acc, next -> acc + next }
      assertEquals(0, emptySum)

      //power of changing result type
      val (even, odd) = numbers.fold(Pair(mutableListOf<Int>(), mutableListOf<Int>())) { eoPair, number ->
         eoPair.apply {
            when (number % 2) {
               0 -> first += number
               else -> second += number
            }
         }
      }

      assertEquals(listOf(2), even)
      assertEquals(listOf(1, 3), odd)
   }

   @Test
   fun testVariationsOfFold() {
      val numbers = listOf(1, 2, 3)
      val reversed = numbers.foldRight(listOf<Int>()) { next, acc -> acc + next }
      assertEquals(listOf(3, 2, 1), reversed)

      val reversedIndexes = numbers.foldRightIndexed(listOf<Int>()) { i, _, acc -> acc + i }
      assertEquals(listOf(2, 1, 0), reversedIndexes)
   }


}