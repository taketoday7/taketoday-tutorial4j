package cn.tuyucheng.taketoday.datetime.sql;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeUtilsUnitTest {

   @Test(expected = IllegalArgumentException.class)
   public void givenTimeAsString_whenPatternIsNotRespected_thenExceptionIsThrown() {
      TimeUtils.getTime("10 11 12");
   }

   @Test
   public void givenTimeAndPattern_thenTimeIsCorrectlyReturned() throws ParseException {
      assertEquals(TimeUtils.getTime("10:11:12"), TimeUtils.getTime("10 11 12", "hh mm ss"));
   }
}