package cn.tuyucheng.taketoday.timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToTimestampConverterUnitTest {

   @Test
   public void givenDatePattern_whenParsing_thenTimestampIsCorrect() {
      String pattern = "MMM dd, yyyy HH:mm:ss.SSSSSSSS";
      String timestampAsString = "Nov 12, 2018 13:02:56.12345678";
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
      LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(timestampAsString));

      Timestamp timestamp = Timestamp.valueOf(localDateTime);
      Assertions.assertEquals("2018-11-12 13:02:56.12345678", timestamp.toString());
   }
}