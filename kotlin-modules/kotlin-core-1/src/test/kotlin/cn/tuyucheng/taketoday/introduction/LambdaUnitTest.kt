package cn.tuyucheng.taketoday.introduction

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class LambdaUnitTest {

   @Test
   fun givenListOfNumber_whenDoingOperationsUsingLambda_shouldReturnProperResult() {
      //given
      val listOfNumbers = listOf(1, 2, 3)

      //when
      val sum = listOfNumbers.reduce { a, b -> a + b }

      //then
      assertEquals(6, sum)
   }
}