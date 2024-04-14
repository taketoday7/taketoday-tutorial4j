package cn.tuyucheng.taketoday.algorithms.quicksort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BentleyMcilroyPartitioningUnitTest {

   @Test
   void given_IntegerArray_whenSortedWithBentleyMcilroyPartitioning_thenGetSortedArray() {
      int[] actual = {3, 2, 2, 2, 3, 7, 7, 3, 2, 2, 7, 3, 3};
      int[] expected = {2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 7, 7, 7};
      BentleyMcIlroyPartioning.quicksort(actual, 0, actual.length - 1);
      assertArrayEquals(expected, actual);
   }

}
