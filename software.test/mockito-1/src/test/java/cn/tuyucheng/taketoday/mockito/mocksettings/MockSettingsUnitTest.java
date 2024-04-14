package cn.tuyucheng.taketoday.mockito.mocksettings;

import cn.tuyucheng.taketoday.mockito.fluentapi.Pizza;
import cn.tuyucheng.taketoday.mockito.fluentapi.PizzaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Answers.RETURNS_SMART_NULLS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockSettingsUnitTest {

   @Test
   public void whenServiceMockedWithSmartNulls_thenExceptionHasExtraInfo() {
      PizzaService service = mock(PizzaService.class, withSettings().defaultAnswer(RETURNS_SMART_NULLS));
      Pizza pizza = service.orderHouseSpecial();
      assertThrows(SmartNullPointerException.class, pizza::getSize);
   }

   @Test
   public void whenServiceMockedWithNameAndVerboseLogging_thenLogsMethodInvocations() {
      PizzaService service = mock(PizzaService.class, withSettings().name("pizzaServiceMock")
            .verboseLogging());

      Pizza pizza = mock(Pizza.class);
      when(service.orderHouseSpecial()).thenReturn(pizza);

      service.orderHouseSpecial();

      verify(service).orderHouseSpecial();
   }

   @Test
   public void whenServiceMockedWithExtraInterfaces_thenConstructorSuccess() {
      SpecialInterface specialMock = mock(SpecialInterface.class, withSettings().extraInterfaces(Runnable.class));
      new SimpleService(specialMock);
   }

   @Test
   public void whenMockSetupWithConstructor_thenConstructorIsInvoked() {
      AbstractCoffee coffeeSpy = mock(AbstractCoffee.class, withSettings().useConstructor("espresso")
            .defaultAnswer(CALLS_REAL_METHODS));

      assertEquals("espresso", coffeeSpy.getName(), "Coffee name: ");
   }
}