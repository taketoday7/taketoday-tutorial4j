package cn.tuyucheng.taketoday.testpropertysource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClassUsingProperty.class)
@TestPropertySource(locations = "/other-location.properties", properties = "tuyucheng.testpropertysource.one=other-properties-value")
class PropertiesTestPropertySourceIntegrationTest {

   @Autowired
   ClassUsingProperty classUsingProperty;

   @Test
   void givenACustomPropertySource_whenVariableOneRetrieved_thenValueInPropertyAnnotationIsReturned() {
      String output = classUsingProperty.retrievePropertyOne();

      assertThat(output).isEqualTo("other-properties-value");
   }
}