package cn.tuyucheng.taketoday.java14.npe;

import org.junit.jupiter.api.Test;

import static cn.tuyucheng.taketoday.java14.helpfulnullpointerexceptions.HelpfulNullPointerException.Employee;
import static cn.tuyucheng.taketoday.java14.helpfulnullpointerexceptions.HelpfulNullPointerException.PersonalDetails;
import static org.assertj.core.api.Assertions.assertThat;

public class HelpfulNullPointerExceptionUnitTest {

   @Test(expected = NullPointerException.class)
   public void givenAnEmptyPersonalDetails_whenEmailAddressIsAccessed_thenThrowNPE() {
      var helpfulNPE = new HelpfulNullPointerException();

      var employee = new Employee();
      employee.setName("Eduard");
      employee.setPersonalDetails(new PersonalDetails());
      helpfulNPE.getEmployeeEmailAddress(employee);
   }

   @Test
   public void givenCompletePersonalDetails_whenEmailAddressIsAccessed_thenSuccess() {
      var helpfulNPE = new HelpfulNullPointerException();
      var emailAddress = "eduard@gmx.com";

      var employee = new Employee();
      employee.setName("Eduard");

      var personalDetails = new PersonalDetails();
      personalDetails.setEmailAddress(emailAddress.toUpperCase());
      personalDetails.setPhone("1234");
      employee.setPersonalDetails(personalDetails);

      assertThat(helpfulNPE.getEmployeeEmailAddress(employee)).isEqualTo(emailAddress);
   }

}
