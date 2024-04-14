package cn.tuyucheng.taketoday.spring.cloud.aws.ec2;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * To run this Live Test, we need to have an AWS account and have API keys generated for programmatic access.
 * <p>
 * Check the README file in this module for more information.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application-test.properties")
class EC2MetadataLiveTest {

   private static final Logger logger = LoggerFactory.getLogger(EC2MetadataLiveTest.class);

   private boolean serverEc2;

   @BeforeEach
   void setUp() {
      serverEc2 = Regions.getCurrentRegion() != null;
   }

   @Autowired
   private EC2Metadata eC2Metadata;

   @Autowired
   private AmazonEC2 amazonEC2;

   @Test
   void whenEC2ClientNotNull_thenSuccess() {
      assertThat(amazonEC2).isNotNull();
   }

   @Test
   void whenEC2MetadataNotNull_thenSuccess() {
      assertThat(eC2Metadata).isNotNull();
   }

   @Test
   void whenMetadataValuesNotNull_thenSuccess() {
      assumeTrue(serverEc2);
      assertThat(eC2Metadata.getAmiId()).isNotEqualTo("N/A");
      assertThat(eC2Metadata.getInstanceType()).isNotEqualTo("N/A");
   }

   @Test
   void whenMetadataLogged_thenSuccess() {
      logger.info("Environment is EC2: {}", serverEc2);
      logger.info(eC2Metadata.toString());
   }
}