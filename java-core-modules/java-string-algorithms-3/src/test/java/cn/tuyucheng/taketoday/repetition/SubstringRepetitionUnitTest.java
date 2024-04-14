package cn.tuyucheng.taketoday.repetition;

import org.junit.jupiter.api.Test;

import static cn.tuyucheng.taketoday.repetition.SubstringRepetition.containsOnlySubstrings;
import static cn.tuyucheng.taketoday.repetition.SubstringRepetition.containsOnlySubstringsEfficient;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubstringRepetitionUnitTest {

   private String validString = "aa";
   private String validStringTwo = "ababab";
   private String validStringThree = "tuyuchengtuyucheng";

   private String invalidString = "aca";
   private String invalidStringTwo = "ababa";
   private String invalidStringThree = "tuyuchengnonrepeatedtuyucheng";

   @Test
   public void givenValidStrings_whenCheckIfContainsOnlySubstrings_thenReturnsTrue() {
      assertTrue(containsOnlySubstrings(validString));
      assertTrue(containsOnlySubstrings(validStringTwo));
      assertTrue(containsOnlySubstrings(validStringThree));
   }

   @Test
   public void givenInvalidStrings_whenCheckIfContainsOnlySubstrings_thenReturnsFalse() {
      assertFalse(containsOnlySubstrings(invalidString));
      assertFalse(containsOnlySubstrings(invalidStringTwo));
      assertFalse(containsOnlySubstrings(invalidStringThree));
   }

   @Test
   public void givenValidStrings_whenCheckEfficientlyIfContainsOnlySubstrings_thenReturnsTrue() {
      assertTrue(containsOnlySubstringsEfficient(validString));
      assertTrue(containsOnlySubstringsEfficient(validStringTwo));
      assertTrue(containsOnlySubstringsEfficient(validStringThree));
   }

   @Test
   public void givenInvalidStrings_whenCheckEfficientlyIfContainsOnlySubstrings_thenReturnsFalse() {
      assertFalse(containsOnlySubstringsEfficient(invalidString));
      assertFalse(containsOnlySubstringsEfficient(invalidStringTwo));
      assertFalse(containsOnlySubstringsEfficient(invalidStringThree));
   }
}
