package cn.tuyucheng.taketoday.ipv4validation;

import org.junit.jupiter.api.Test;

import static cn.tuyucheng.taketoday.urlvalidation.IPv4Validation.validateWithApacheCommons;
import static cn.tuyucheng.taketoday.urlvalidation.IPv4Validation.validateWithGuava;
import static cn.tuyucheng.taketoday.urlvalidation.IPv4Validation.validateWithRegex;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IPv4ValidationUnitTest {

   @Test
   public void givenValidIPv4_whenValidate_thenReturnsTrue() {
      String ip = "192.168.0.1";
      assertTrue(validateWithApacheCommons(ip));
      assertTrue(validateWithGuava(ip));
      assertTrue(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithThreeOctets_whenValidate_thenReturnsFalse() {
      String ip = "192.168.0";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithLeadingZero_whenValidate_thenReturnsFalse() {
      String ip = "192.168.0.01";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithInvalidCharacter_whenValidate_thenReturnsFalse() {
      String ip = "a.168.0.01";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4HaveValueAbove255_whenValidate_thenReturnsFalse() {
      String ip = "192.168.256.1";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenValidIPv4WithTwoDigitOctet_whenValidate_thenReturnsTrue() {
      String ip = "192.168.42.1";
      assertTrue(validateWithApacheCommons(ip));
      assertTrue(validateWithGuava(ip));
      assertTrue(validateWithRegex(ip));
   }

   @Test
   public void givenValidIPv4WithNumberInRange200And249_whenValidate_thenReturnsTrue() {
      String ip = "192.168.42.222";
      assertTrue(validateWithApacheCommons(ip));
      assertTrue(validateWithGuava(ip));
      assertTrue(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithFourDigitOctet_whenValidate_thenReturnsFalse() {
      String ip = "1921.168.42.222";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithFiveOctets_whenValidate_thenReturnsFalse() {
      String ip = "192.168.42.222.10";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4WithTwoConsecutiveDots_whenValidate_thenReturnsFalse() {
      String ip = "192.168..1";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenOnlyDots_whenValidate_thenReturnsFalse() {
      String ip = "...";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenBlankString_whenValidate_thenReturnsFalse() {
      String ip = "  ";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4StartWithDot_whenValidate_thenReturnsFalse() {
      String ip = ".192.168.0.1";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

   @Test
   public void givenIPv4EndWithDot_whenValidate_thenReturnsFalse() {
      String ip = "192.168.0.1.";
      assertFalse(validateWithApacheCommons(ip));
      assertFalse(validateWithGuava(ip));
      assertFalse(validateWithRegex(ip));
   }

}
