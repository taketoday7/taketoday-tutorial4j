package cn.tuyucheng.taketoday.boot.countrows.accountstatslogic;

import cn.tuyucheng.taketoday.countrows.AccountStatsApplication;
import cn.tuyucheng.taketoday.countrows.entity.Account;
import cn.tuyucheng.taketoday.countrows.entity.Permission;
import cn.tuyucheng.taketoday.countrows.repository.AccountRepository;
import cn.tuyucheng.taketoday.countrows.repository.PermissionRepository;
import cn.tuyucheng.taketoday.countrows.service.AccountStatsLogic;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = AccountStatsApplication.class)
class AccountStatsUnitTest {

   @Autowired
   private PermissionRepository permissionRepository;

   @Autowired
   private AccountRepository accountRepository;

   @Autowired
   private AccountStatsLogic accountStatsLogic;

   @AfterEach
   void afterEach() {
      accountRepository.deleteAll();
      permissionRepository.deleteAll();
   }

   @Test
   void givenAccountInTable_whenPerformCount_returnsAppropriateCount() {
      savePermissions();
      saveAccount();
      assertThat(accountStatsLogic.getAccountCount()).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByUsernameOrPermission_returnsAppropriateCount() {
      savePermissions();
      Account account = saveAccount();
      assertThat(accountStatsLogic.getAccountCountByUsername(account.getUsername())).isEqualTo(1);
      assertThat(accountStatsLogic.getAccountCountByPermission(account.getPermission())).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByPermissionAndCreatedOn_returnsAppropriateCount() throws ParseException {
      savePermissions();
      Account account = saveAccount();
      long count = accountStatsLogic.getAccountCountByPermissionAndCreatedOn(account.getPermission(), account.getCreatedOn());
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountUsingCQ_returnsAppropriateCount() throws ParseException {
      savePermissions();
      saveAccount();
      long count = accountStatsLogic.getAccountsUsingCQ();
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByPermissionUsingCQ_returnsAppropriateCount() throws ParseException {
      savePermissions();
      Account account = saveAccount();
      long count = accountStatsLogic.getAccountsByPermissionUsingCQ(account.getPermission());
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByPermissionAndCreatedOnUsingCQ_returnsAppropriateCount() throws ParseException {
      savePermissions();
      Account account = saveAccount();
      long count = accountStatsLogic.getAccountsByPermissionAndCreateOnUsingCQ(account.getPermission(), account.getCreatedOn());
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountUsingJPQL_returnsAppropriateCount() throws ParseException {
      savePermissions();
      saveAccount();
      long count = accountStatsLogic.getAccountsUsingJPQL();
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByPermissionUsingJPQL_returnsAppropriateCount() throws ParseException {
      savePermissions();
      Account account = saveAccount();
      long count = accountStatsLogic.getAccountsByPermissionUsingJPQL(account.getPermission());
      assertThat(count).isEqualTo(1);
   }

   @Test
   void givenAccountInTable_whenPerformCountByPermissionAndCreatedOnUsingJPQL_returnsAppropriateCount() throws ParseException {
      savePermissions();
      Account account = saveAccount();
      long count = accountStatsLogic.getAccountsByPermissionAndCreatedOnUsingJPQL(account.getPermission(), account.getCreatedOn());
      assertThat(count).isEqualTo(1);
   }

   private Account saveAccount() {
      return accountRepository.save(getAccount());
   }

   private void savePermissions() {
      Permission editor = new Permission();
      editor.setType("editor");
      permissionRepository.save(editor);

      Permission admin = new Permission();
      admin.setType("admin");
      permissionRepository.save(admin);
   }

   private Account getAccount() {
      Permission permission = permissionRepository.findByType("admin");
      Account account = new Account();
      String seed = UUID.randomUUID()
            .toString();
      account.setUsername("username_" + seed);
      account.setEmail("username_" + seed + "@gmail.com");
      account.setPermission(permission);
      account.setPassword("password_q1234");
      account.setCreatedOn(Timestamp.from(Instant.now()));
      account.setLastLogin(Timestamp.from(Instant.now()));
      return account;
   }
}