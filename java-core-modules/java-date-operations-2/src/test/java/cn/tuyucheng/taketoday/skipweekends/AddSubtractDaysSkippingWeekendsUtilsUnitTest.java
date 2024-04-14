package cn.tuyucheng.taketoday.skipweekends;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSubtractDaysSkippingWeekendsUtilsUnitTest {

   @Test
   public void givenLocalDateAndDaysToAdd_thenAddDaysSkippingWeekends() {
      LocalDate initialDate = LocalDate.of(2019, 11, 7);
      LocalDate expectedDate = LocalDate.of(2019, 11, 13);
      LocalDate result = AddSubtractDaysSkippingWeekendsUtils.addDaysSkippingWeekends(initialDate, 4);
      assertEquals(expectedDate, result);
   }

   @Test
   public void givenLocalDateAndDaysToSubtract_thenSubtractDaysSkippingWeekends() {
      LocalDate initialDate = LocalDate.of(2019, 11, 7);
      LocalDate expectedDate = LocalDate.of(2019, 11, 1);
      LocalDate result = AddSubtractDaysSkippingWeekendsUtils.subtractDaysSkippingWeekends(initialDate, 4);
      assertEquals(expectedDate, result);
   }

}
