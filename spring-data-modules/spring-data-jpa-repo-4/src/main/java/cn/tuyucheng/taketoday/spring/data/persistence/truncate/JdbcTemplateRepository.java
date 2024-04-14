package cn.tuyucheng.taketoday.spring.data.persistence.truncate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcTemplateRepository {
   private final JdbcTemplate jdbcTemplate;

   public JdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
      this.jdbcTemplate = jdbcTemplate;
   }

   @Transactional
   public void truncateTable(String tableName) {
      String sql = STR."TRUNCATE TABLE \{tableName}";
      jdbcTemplate.execute(sql);
   }
}