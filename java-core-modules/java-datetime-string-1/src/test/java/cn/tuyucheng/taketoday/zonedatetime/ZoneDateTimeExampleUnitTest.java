package cn.tuyucheng.taketoday.zonedatetime;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZoneDateTimeExampleUnitTest {

   ZoneDateTimeExample zoneDateTimeExample = new ZoneDateTimeExample();

   @Test
   public void givenZone_whenGetCurrentTime_thenResultHasZone() {
      String zone = "Europe/Berlin";
      ZonedDateTime time = zoneDateTimeExample.getCurrentTimeByZoneId(zone);

      assertTrue(time.getZone()
            .equals(ZoneId.of(zone)));
   }

   @Test
   public void givenZones_whenConvertDateByZone_thenGetConstantDiff() {
      String sourceZone = "Europe/Berlin";
      String destZone = "Asia/Tokyo";
      ZonedDateTime sourceDate = zoneDateTimeExample.getCurrentTimeByZoneId(sourceZone);
      ZonedDateTime destDate = zoneDateTimeExample.convertZonedDateTime(sourceDate, destZone);

      assertTrue(sourceDate.toInstant()
            .compareTo(destDate.toInstant()) == 0);
   }
}
