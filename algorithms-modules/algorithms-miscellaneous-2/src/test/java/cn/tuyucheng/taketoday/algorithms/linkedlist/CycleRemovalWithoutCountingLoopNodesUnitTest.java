package cn.tuyucheng.taketoday.algorithms.linkedlist;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class CycleRemovalWithoutCountingLoopNodesUnitTest extends CycleDetectionTestBase {

   @ParameterizedTest
   @MethodSource("getLists")
   void givenList_ifLoopExists_thenDetectAndRemoveLoop(Node<Integer> head, boolean cycleExists) {
      assertEquals(cycleExists, CycleRemovalWithoutCountingLoopNodes.detectAndRemoveCycle(head));
      assertFalse(CycleDetectionByFastAndSlowIterators.detectCycle(head).cycleExists);
   }
}