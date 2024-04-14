package cn.tuyucheng.taketoday.alphanumeric;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlphanumericUnitTest {

   private AlphanumericPerformanceBenchmark alphanumericPerformanceBenchmark = new AlphanumericPerformanceBenchmark();

   @ParameterizedTest
   @CsvSource({
         "A,true",
         "B,true",
         "C,true",
         "1,true",
         "2,true",
         "3,true",
         "!,false",
         "@,false",
         "#,false",
         "$,false",
         "%,false"
   })
   void shouldCorrectlyIdentifyAlphanumericCharacterTest(char character, boolean result) {
      boolean actual = alphanumericPerformanceBenchmark.isAlphanumeric(character);
      assertEquals(actual, result);
   }
}