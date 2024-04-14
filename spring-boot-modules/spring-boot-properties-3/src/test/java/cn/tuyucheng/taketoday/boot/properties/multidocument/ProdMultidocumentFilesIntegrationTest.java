package cn.tuyucheng.taketoday.boot.properties.multidocument;

import cn.tuyucheng.taketoday.boot.properties.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {DemoApplication.class}, webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("multidocument-prod")
class ProdMultidocumentFilesIntegrationTest {

   @Value("${tuyucheng.property}")
   private String tuyuchengCustomProperty;

   @Value("${tuyucheng.root-level-property}")
   private String tuyuchengRootProperty;

   @Test
   void givenProductionProfileActive_whenApplicationStarts_thenDefaultPropertiesUser() {
      assertThat(tuyuchengCustomProperty).isEqualTo("prodValue");
      assertThat(tuyuchengRootProperty).isEqualTo("defaultRootLevelValue");
   }
}