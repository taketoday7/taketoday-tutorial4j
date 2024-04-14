package cn.tuyucheng.taketoday.temporaladjusters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TemporalAdjustersUnitTest {

   @Test
   public void whenAdjust_thenNextSunday() {
      LocalDate localDate = LocalDate.of(2017, 07, 8);
      LocalDate nextSunday = localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

      String expected = "2017-07-09";

      Assertions.assertEquals(expected, nextSunday.toString());
   }

}
