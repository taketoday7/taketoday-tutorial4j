package cn.tuyucheng.taketoday.inheritancecomposition;

import cn.tuyucheng.taketoday.inheritancecomposition.model.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonUnitTest {

   private static Person person;

   @BeforeAll
   public static void setPersonInstance() {
      person = new Person("John", "john@domain.com", 35);
   }

   @Test
   public void givenPersonInstance_whenCalledgetName_thenEqual() {
      assertThat(person.getName()).isEqualTo("John");
   }

   @Test
   public void givenPersonInstance_whenCalledgetEmail_thenEqual() {
      assertThat(person.getEmail()).isEqualTo("john@domain.com");
   }

   @Test
   public void givenPersonInstance_whenCalledgetAge_thenEqual() {
      assertThat(person.getAge()).isEqualTo(35);
   }
}
