package cn.tuyucheng.taketoday.substringsearch;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubstringSearchUnitTest {

   @Test
   public void searchSubstringWithIndexOf() {
      Assertions.assertEquals(9, "Bohemian Rhapsodyan".indexOf("Rhap"));

      // indexOf will return -1, because it's case sensitive
      Assertions.assertEquals(-1, "Bohemian Rhapsodyan".indexOf("rhap"));

      // indexOf will return 9, because it's all lowercase
      Assertions.assertEquals(9, "Bohemian Rhapsodyan".toLowerCase()
            .indexOf("rhap"));

      // it will return 6, because it's the first occurrence. Sorry Queen for being blasphemic
      Assertions.assertEquals(6, "Bohemian Rhapsodyan".indexOf("an"));
   }

   @Test
   public void searchSubstringWithContains() {
      Assertions.assertTrue("Hey Ho, let's go".contains("Hey"));

      // contains will return false, because it's case sensitive
      Assertions.assertFalse("Hey Ho, let's go".contains("hey"));

      // contains will return true, because it's all lowercase
      Assertions.assertTrue("Hey Ho, let's go".toLowerCase().contains("hey"));

      // contains will return false, because 'jey' can't be found
      Assertions.assertFalse("Hey Ho, let's go".contains("jey"));
   }

   @Test
   public void searchSubstringWithStringUtils() {
      Assertions.assertTrue(StringUtils.containsIgnoreCase("Runaway train", "train"));

      // it will also be true, because ignores case ;)
      Assertions.assertTrue(StringUtils.containsIgnoreCase("Runaway train", "Train"));
   }

   @Test
   public void searchUsingPattern() {

      // We create the Pattern first
      Pattern pattern = Pattern.compile("(?<!\\S)" + "road" + "(?!\\S)");

      // We need to create the Matcher after
      Matcher matcher = pattern.matcher("Hit the road Jack");

      // find will return true when the first match is found
      Assertions.assertTrue(matcher.find());

      // We will create a different matcher with a different text
      matcher = pattern.matcher("and don't you come back no more");

      // find will return false, because 'road' can't be find as a substring
      Assertions.assertFalse(matcher.find());
   }
}