package cn.tuyucheng.taketoday.datetime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class UseTimeZoneUnitTest {

   /* https://en.wikipedia.org/wiki/List_of_tz_database_time_zones */

   String timeZone = "Asia/Singapore";

   private static final String PATTERN = "E yyyy-MM-dd HH:mm:ss a";

   @Test
   public void givenDateWithoutTimeZone_WhenSetTimeZoneUsingJava7_ThenTimeZoneIsSetSuccessfully() {
      Date nowUtc = new Date();
      TimeZone asiaSingapore = TimeZone.getTimeZone(timeZone);

      Calendar nowAsiaSingapore = Calendar.getInstance(asiaSingapore);
      nowAsiaSingapore.setTime(nowUtc);

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
      simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));

      System.out.println(String.format("Java7: Time now in '%s' is '%s'", nowAsiaSingapore.getTimeZone()
            .getID(), simpleDateFormat.format(nowAsiaSingapore.getTime())));

      Assertions.assertEquals(nowUtc.toInstant().getEpochSecond(), nowAsiaSingapore.toInstant().getEpochSecond());
      Assertions.assertEquals(asiaSingapore, nowAsiaSingapore.getTimeZone());

   }

   @Test
   public void givenDateWithoutTimeZone_WhenSetTimeZoneUsingJava8_ThenTimeZoneIsSetSuccessfully() {
      Instant nowUtc = Instant.now();
      ZoneId asiaSingapore = ZoneId.of(timeZone);

      ZonedDateTime nowAsiaSingapore = ZonedDateTime.ofInstant(nowUtc, asiaSingapore);

      System.out.println(String.format("Java8: Time now in '%s' is '%s'", nowAsiaSingapore.getZone(),
            nowAsiaSingapore.format(DateTimeFormatter.ofPattern(PATTERN))));

      Assertions.assertEquals(nowUtc.getEpochSecond(), nowAsiaSingapore.toEpochSecond());
      Assertions.assertEquals(asiaSingapore, nowAsiaSingapore.getZone());
   }

   @Test
   public void givenDateWithoutTimeZone_WhenSetTimeZoneUsingJodaTime_ThenTimeZoneIsSetSuccessfully() {
      org.joda.time.Instant nowUtc = org.joda.time.Instant.now();
      DateTimeZone asiaSingapore = DateTimeZone.forID(timeZone);

      DateTime nowAsiaSingapore = nowUtc.toDateTime(asiaSingapore);

      System.out.println(String.format("Joda-time: Time now in '%s' is '%s'", nowAsiaSingapore.getZone(),
            nowAsiaSingapore.toString(PATTERN)));

      Assertions.assertEquals(nowUtc.toInstant().getMillis(), nowAsiaSingapore.toInstant().getMillis());
      Assertions.assertEquals(asiaSingapore, nowAsiaSingapore.getZone());
   }

}
