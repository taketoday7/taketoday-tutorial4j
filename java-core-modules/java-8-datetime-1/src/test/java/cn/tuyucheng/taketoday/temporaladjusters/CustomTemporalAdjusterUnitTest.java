package cn.tuyucheng.taketoday.temporaladjusters;

import cn.tuyucheng.taketoday.temporaladjuster.CustomTemporalAdjuster;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjuster;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomTemporalAdjusterUnitTest {

   private static final TemporalAdjuster NEXT_WORKING_DAY = new CustomTemporalAdjuster();

   @Test
   public void whenAdjustAndImplementInterface_thenNextWorkingDay() {
      LocalDate localDate = LocalDate.of(2017, 07, 8);
      CustomTemporalAdjuster temporalAdjuster = new CustomTemporalAdjuster();
      LocalDate nextWorkingDay = localDate.with(temporalAdjuster);

      assertEquals("2017-07-10", nextWorkingDay.toString());
   }

   @Test
   public void whenAdjust_thenNextWorkingDay() {
      LocalDate localDate = LocalDate.of(2017, 07, 8);
      LocalDate date = localDate.with(NEXT_WORKING_DAY);

      assertEquals("2017-07-10", date.toString());
   }

   @Test
   public void whenAdjust_thenFourteenDaysAfterDate() {
      LocalDate localDate = LocalDate.of(2017, 07, 8);
      TemporalAdjuster temporalAdjuster = (t) -> t.plus(Period.ofDays(14));
      LocalDate result = localDate.with(temporalAdjuster);

      String fourteenDaysAfterDate = "2017-07-22";

      assertEquals(fourteenDaysAfterDate, result.toString());
   }
}