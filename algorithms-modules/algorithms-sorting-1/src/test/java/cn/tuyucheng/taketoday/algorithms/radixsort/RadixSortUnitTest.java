package cn.tuyucheng.taketoday.algorithms.radixsort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RadixSortUnitTest {

   @Test
   void givenUnsortedArray_whenRadixSort_thenArraySorted() {
      int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7};
      RadixSort.sort(numbers);
      int[] numbersSorted = {7, 37, 68, 123, 134, 221, 387, 468, 769};
      assertArrayEquals(numbersSorted, numbers);
   }
}
