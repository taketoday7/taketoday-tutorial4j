package cn.tuyucheng.taketoday.spring.cloud.aws.sqs;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;

@Testcontainers
public class BaseSqsLiveTest {

   private static final String LOCAL_STACK_VERSION = "localstack/localstack:2.3.2";

   @Container
   static LocalStackContainer localStack = new LocalStackContainer(DockerImageName.parse(LOCAL_STACK_VERSION));

   @DynamicPropertySource
   static void overrideProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.cloud.aws.region.static", () -> localStack.getRegion());
      registry.add("spring.cloud.aws.credentials.access-key", () -> localStack.getAccessKey());
      registry.add("spring.cloud.aws.credentials.secret-key", () -> localStack.getSecretKey());
      registry.add("spring.cloud.aws.sqs.endpoint", () -> localStack.getEndpointOverride(SQS)
            .toString());
   }
}