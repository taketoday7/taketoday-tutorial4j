package cn.tuyucheng.taketoday.algorithms.editdistance;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditDistanceUnitTest {

   static Stream<? extends Arguments> provideArguments() {
      return Stream.of(new Object[][]{
            {"", "", 0},
            {"ago", "", 3},
            {"", "do", 2},
            {"abc", "adc", 1},
            {"peek", "pesek", 1},
            {"sunday", "saturday", 3}
      }).map(Arguments::of);
   }

   @ParameterizedTest
   @MethodSource("provideArguments")
   void testEditDistance_RecursiveImplementation(String x, String y, int result) {
      assertEquals(result, EditDistanceRecursive.calculate(x, y));
   }

   @ParameterizedTest
   @MethodSource("provideArguments")
   void testEditDistance_givenDynamicProgrammingImplementation(String x, String y, int result) {
      assertEquals(result, EditDistanceDynamicProgramming.calculate(x, y));
   }
}
