package cn.tuyucheng.taketoday.datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocalDateExtractYearMonthDayIntegerValuesUnitTest {

   LocalDateExtractYearMonthDayIntegerValues localDateExtractYearMonthDayIntegerValues = new LocalDateExtractYearMonthDayIntegerValues();

   LocalDate localDate = LocalDate.parse("2007-12-03");

   @Test
   public void whenGetYear_thenCorrectYear() {
      int actualYear = localDateExtractYearMonthDayIntegerValues.getYear(localDate);
      assertThat(actualYear, is(2007));
   }

   @Test
   public void whenGetMonth_thenCorrectMonth() {
      int actualMonth = localDateExtractYearMonthDayIntegerValues.getMonth(localDate);
      assertThat(actualMonth, is(12));
   }

   @Test
   public void whenGetDay_thenCorrectDay() {
      int actualDayOfMonth = localDateExtractYearMonthDayIntegerValues.getDay(localDate);
      assertThat(actualDayOfMonth, is(03));
   }
}
