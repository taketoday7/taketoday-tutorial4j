package cn.tuyucheng.taketoday.exception.missingreturnstatement;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MissingReturnStatementUnitTest {

   @Test
   public void givenANumber_thenReturnItsPow() {
      int number = 10;
      int pow = MissingReturnStatement.pow(number);
      assertEquals(100, pow);
   }

   @Test
   public void givenABigNumber_thenReturnItsType() {
      int number = 200;
      String type = MissingReturnStatement.checkNumber(number);
      assertEquals("It's a big number", type);
   }

   @Test
   public void givenANegativeNumber_thenReturnItsType() {
      int number = -10;
      String type = MissingReturnStatement.checkNumber(number);
      assertEquals("It's a negative number", type);
   }

   @Test
   public void getStringDictionary_thenPrintValues() {
      Map<String, Integer> dictionary = MissingReturnStatement.createDictionary();
      assertEquals(2, dictionary.size());
      dictionary.forEach((s, integer) -> System.out.println(s + " - " + integer));
   }
}