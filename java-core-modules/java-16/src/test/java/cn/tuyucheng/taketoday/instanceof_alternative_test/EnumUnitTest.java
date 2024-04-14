package cn.tuyucheng.taketoday.instanceof_alternative_test;

import cn.tuyucheng.taketoday.instanceof_alternatives.enumallt.DinosaurEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumUnitTest {
   @Test
   public void givenADinosaurSpecie_whenUsingEnum_thenGetMovementOfEuraptor() {

      assertEquals("flying", moveDinosaurUsingEnum(DinosaurEnum.Euraptor));
   }

   @Test
   public void givenADinosaurSpecie_whenUsingEnum_thenGetMovementOfAnatotitan() {
      assertEquals("running", moveDinosaurUsingEnum(DinosaurEnum.Anatotitan));
   }

   public static String moveDinosaurUsingEnum(DinosaurEnum dinosaurenum) {
      return dinosaurenum.move();

   }
}