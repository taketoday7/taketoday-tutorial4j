package cn.tuyucheng.taketoday.varargs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FormatterUnitTest {

   private final static String FORMAT = "%s %s %s";

   @Test
   public void givenNoArgument_thenEmptyAndTwoSpacesAreReturned() {
      String actualResult = format();

      assertThat(actualResult, is("empty  "));
   }

   @Test
   public void givenOneArgument_thenResultHasTwoTrailingSpace() {
      String actualResult = format("tuyucheng");

      assertThat(actualResult, is("tuyucheng  "));
   }

   @Test
   public void givenTwoArguments_thenOneTrailingSpaceExists() {
      String actualResult = format("tuyucheng", "rocks");

      assertThat(actualResult, is("tuyucheng rocks "));
   }

   @Test
   public void givenMoreThanThreeArguments_thenTheFirstThreeAreUsed() {
      String actualResult = formatWithVarArgs("tuyucheng", "rocks", "java", "and", "spring");

      assertThat(actualResult, is("tuyucheng rocks java"));
   }

   public String format() {
      return format("empty", "");
   }

   public String format(String value) {
      return format(value, "");
   }

   public String format(String val1, String val2) {
      return String.format(FORMAT, val1, val2, "");
   }

   public String formatWithVarArgs(String... values) {
      if (values.length == 0) {
         return "no arguments given";
      }

      return String.format(FORMAT, values);
   }

}