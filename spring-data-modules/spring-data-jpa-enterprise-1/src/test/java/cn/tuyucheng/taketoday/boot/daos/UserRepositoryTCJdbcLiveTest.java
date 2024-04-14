package cn.tuyucheng.taketoday.boot.daos;

import cn.tuyucheng.taketoday.boot.Application;
import cn.tuyucheng.taketoday.boot.daos.user.UserRepository;
import cn.tuyucheng.taketoday.boot.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("tc-jdbc")
@SpringBootTest(classes = Application.class)
class UserRepositoryTCJdbcLiveTest {

   final String USER_EMAIL = "email@example.com";
   final String USER_EMAIL2 = "email2@example.com";
   final String USER_EMAIL3 = "email3@example.com";
   final String USER_EMAIL4 = "email4@example.com";
   final Integer INACTIVE_STATUS = 0;
   final Integer ACTIVE_STATUS = 1;

   @Autowired
   private UserRepository userRepository;

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
}