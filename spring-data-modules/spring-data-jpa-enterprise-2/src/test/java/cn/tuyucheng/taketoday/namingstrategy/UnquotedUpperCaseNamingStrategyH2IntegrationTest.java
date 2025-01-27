package cn.tuyucheng.taketoday.namingstrategy;

import org.assertj.core.api.Assertions;
import org.hibernate.exception.SQLGrammarException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
@TestPropertySource("unquoted-upper-case-naming-strategy.properties")
class UnquotedUpperCaseNamingStrategyH2IntegrationTest {

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   private PersonRepository personRepository;

   @BeforeEach
   void insertPeople() {
      personRepository.saveAll(Arrays.asList(
            new Person(1L, "John", "Doe"),
            new Person(2L, "Jane", "Doe"),
            new Person(3L, "Ted", "Mosby")
      ));
   }

   @ParameterizedTest
   @ValueSource(strings = {"person", "PERSON", "Person"})
   void givenPeopleAndUpperCaseNamingStrategy_whenQueryPersonUnquoted_thenResult(String tableName) {
      Query query = entityManager.createNativeQuery("select * from " + tableName);

      // Expected result
      List<Person> result = (List<Person>) query.getResultStream()
            .map(this::fromDatabase)
            .collect(Collectors.toList());

      Assertions.assertThat(result).isNotEmpty();
   }

   @Test
   void givenPeopleAndUpperCaseNamingStrategy_whenQueryPersonQuotedUpperCase_thenResult() {
      Query query = entityManager.createNativeQuery("select * from \"PERSON\"");

      // Expected result
      List<Person> result = (List<Person>) query.getResultStream()
            .map(this::fromDatabase)
            .collect(Collectors.toList());

      Assertions.assertThat(result).isNotEmpty();
   }

   @Test
   void givenPeopleAndUpperCaseNamingStrategy_whenQueryPersonQuotedLowerCase_thenException() {
      Query query = entityManager.createNativeQuery("select * from \"person\"");

      // Expected result
      assertThrows(SQLGrammarException.class, query::getResultStream);
   }

   public Person fromDatabase(Object databaseRow) {
      Object[] typedDatabaseRow = (Object[]) databaseRow;

      return new Person(
            ((BigInteger) typedDatabaseRow[0]).longValue(),
            (String) typedDatabaseRow[1],
            (String) typedDatabaseRow[2]
      );
   }
}