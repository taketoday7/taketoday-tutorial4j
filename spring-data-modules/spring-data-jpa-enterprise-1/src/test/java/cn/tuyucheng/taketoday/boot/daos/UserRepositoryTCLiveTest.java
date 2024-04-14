package cn.tuyucheng.taketoday.boot.daos;

import cn.tuyucheng.taketoday.boot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@ActiveProfiles("tc")
class UserRepositoryTCLiveTest extends UserRepositoryCommon {

   @Container
   public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
         .withDatabaseName("integration-tests-db")
         .withUsername("sa")
         .withPassword("sa");

   @Test
   @Transactional
   void givenUsersInDB_WhenUpdateStatusForNameModifyingQueryAnnotationNative_ThenModifyMatchingUsers() {
      userRepository.save(new User("SAMPLE", LocalDate.now(), USER_EMAIL, ACTIVE_STATUS));
      userRepository.save(new User("SAMPLE1", LocalDate.now(), USER_EMAIL2, ACTIVE_STATUS));
      userRepository.save(new User("SAMPLE", LocalDate.now(), USER_EMAIL3, ACTIVE_STATUS));
      userRepository.save(new User("SAMPLE3", LocalDate.now(), USER_EMAIL4, ACTIVE_STATUS));
      userRepository.flush();

      int updatedUsersSize = userRepository.updateUserSetStatusForNameNativePostgres(INACTIVE_STATUS, "SAMPLE");

      assertThat(updatedUsersSize).isEqualTo(2);
   }

   @DynamicPropertySource
   static void registerPgProperties(DynamicPropertyRegistry registry) {
      registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
      registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
      registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
   }
}