package cn.tuyucheng.taketoday.interoperability

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CustomerUnitTest {

   @Test
   fun givenCustomer_whenNameAndLastNameAreAssigned_thenComplete() {
      val customer = cn.tuyucheng.taketoday.interoperability.Customer()

      // Setter method is being called
      customer.firstName = "Frodo"
      customer.lastName = "Baggins"

      // Getter method is being called
      assertEquals(customer.firstName, "Frodo")
      assertEquals(customer.lastName, "Baggins")
   }
}