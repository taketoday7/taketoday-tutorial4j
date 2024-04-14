package cn.tuyucheng.taketoday.streams.bigdecimals;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddNumbersUnitTest {

   @Test
   public void givenIntStream_whenSum_thenResultIsCorrect() {
      IntStream intNumbers = IntStream.range(0, 3);
      assertEquals(3, intNumbers.sum());
   }

   @Test
   public void givenCollectionOfDouble_whenUsingMapToDoubleToSum_thenResultIsCorrect() {
      List<Double> doubleNumbers = Arrays.asList(23.48, 52.26, 13.5);
      double result = doubleNumbers.stream()
            .mapToDouble(Double::doubleValue)
            .sum();
      assertEquals(89.24, result, .1);
   }

   public void givenStreamOfIntegers_whenUsingReduceToSum_thenResultIsCorrect() {
      Stream<Integer> intNumbers = Stream.of(0, 1, 2);
      int result = intNumbers.reduce(0, Integer::sum);
      assertEquals(106, result);
   }

   public void givenStreamOfBigDecimals_whenUsingReduceToSum_thenResultIsCorrect() {
      Stream<BigDecimal> bigDecimalNumber = Stream.of(BigDecimal.ZERO, BigDecimal.ONE, BigDecimal.TEN);
      BigDecimal result = bigDecimalNumber.reduce(BigDecimal.ZERO, BigDecimal::add);
      assertEquals(11, result);
   }

}
