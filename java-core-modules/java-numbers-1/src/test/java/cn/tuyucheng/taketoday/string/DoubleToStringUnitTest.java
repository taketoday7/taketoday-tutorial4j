package cn.tuyucheng.taketoday.string;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleToStringUnitTest {

   private static final double DOUBLE_VALUE = 3.56;
   private static final String TRUNCATED_DOUBLE = "3";
   private static final String ROUNDED_UP_DOUBLE = "4";


   @Test
   public void truncateByCastTest() {
      Assertions.assertThat(DoubleToString.truncateByCast(DOUBLE_VALUE)).isEqualTo(TRUNCATED_DOUBLE);
   }

   @Test
   public void roundingWithStringFormatTest() {
      assertThat(DoubleToString.roundWithStringFormat(DOUBLE_VALUE)).isEqualTo(ROUNDED_UP_DOUBLE);
   }

   @Test
   public void truncateWithNumberFormatTest() {
      assertThat(DoubleToString.truncateWithNumberFormat(DOUBLE_VALUE)).isEqualTo(TRUNCATED_DOUBLE);
   }

   @Test
   public void roundWithNumberFormatTest() {
      assertThat(DoubleToString.roundWithNumberFormat(DOUBLE_VALUE)).isEqualTo(ROUNDED_UP_DOUBLE);
   }

   @Test
   public void truncateWithDecimalFormatTest() {
      assertThat(DoubleToString.truncateWithDecimalFormat(DOUBLE_VALUE)).isEqualTo(TRUNCATED_DOUBLE);
   }

   @Test
   public void roundWithDecimalFormatTest() {
      assertThat(DoubleToString.roundWithDecimalFormat(DOUBLE_VALUE)).isEqualTo(ROUNDED_UP_DOUBLE);
   }


}
