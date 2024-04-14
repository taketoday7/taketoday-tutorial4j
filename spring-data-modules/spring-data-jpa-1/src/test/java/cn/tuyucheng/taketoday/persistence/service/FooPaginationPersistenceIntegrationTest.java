package cn.tuyucheng.taketoday.persistence.service;

import cn.tuyucheng.taketoday.config.PersistenceJPAConfig;
import cn.tuyucheng.taketoday.persistence.model.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceJPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext
class FooPaginationPersistenceIntegrationTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(FooPaginationPersistenceIntegrationTest.class);

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   private FooService fooService;

   @BeforeEach
   final void before() {
      final int minimalNumberOfEntities = 25;
      if (fooService.findAll().size() <= minimalNumberOfEntities) {
         for (int i = 0; i < minimalNumberOfEntities; i++) {
            fooService.create(new Foo(randomAlphabetic(6)));
         }
      }
   }

   // tests

   @Test
   final void whenContextIsBootstrapped_thenNoExceptions() {
      //
   }

   @SuppressWarnings("unchecked")
   @Test
   final void givenEntitiesExist_whenRetrievingFirstPage_thenCorrect() {
      final int pageSize = 10;

      final Query query = entityManager.createQuery("From Foo");
      configurePagination(query, 1, pageSize);

      // When
      final List<Foo> fooList = query.getResultList();

      // Then
      assertThat(fooList, hasSize(pageSize));
   }

   @SuppressWarnings("unchecked")
   @Test
   final void givenEntitiesExist_whenRetrievingLastPage_thenCorrect() {
      final int pageSize = 10;
      final Query queryTotal = entityManager.createQuery("Select count(f.id) from Foo f");
      final long countResult = (long) queryTotal.getSingleResult();

      final Query query = entityManager.createQuery("Select f from Foo as f order by f.id");
      final int lastPage = (int) ((countResult / pageSize) + 1);
      configurePagination(query, lastPage, pageSize);
      final List<Foo> fooList = query.getResultList();

      // Then
      assertThat(fooList, hasSize(lessThan(pageSize + 1)));
   }

   @SuppressWarnings("unchecked")
   @Test
   final void givenEntitiesExist_whenRetrievingPage_thenCorrect() {
      final int pageSize = 10;

      final Query queryIds = entityManager.createQuery("Select f.id from Foo f order by f.name");
      final List<Integer> fooIds = queryIds.getResultList();

      final Query query = entityManager.createQuery("Select f from Foo as f where f.id in :ids");
      query.setParameter("ids", fooIds.subList(0, pageSize));

      final List<Foo> fooList = query.getResultList();

      // Then
      assertThat(fooList, hasSize(pageSize));
   }

   @Test
   final void givenEntitiesExist_whenRetrievingPageViaCriteria_thenCorrect() {
      final int pageSize = 10;
      final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      final CriteriaQuery<Foo> criteriaQuery = criteriaBuilder.createQuery(Foo.class);
      final Root<Foo> from = criteriaQuery.from(Foo.class);
      final CriteriaQuery<Foo> select = criteriaQuery.select(from);
      final TypedQuery<Foo> typedQuery = entityManager.createQuery(select);
      typedQuery.setFirstResult(0);
      typedQuery.setMaxResults(pageSize);
      final List<Foo> fooList = typedQuery.getResultList();

      // Then
      assertThat(fooList, hasSize(pageSize));
   }

   @Test
   final void givenEntitiesExist_whenRetrievingPageViaCriteria_thenNoExceptions() {
      int pageNumber = 1;
      final int pageSize = 10;
      final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

      final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
      countQuery.select(criteriaBuilder.count(countQuery.from(Foo.class)));
      final Long count = entityManager.createQuery(countQuery).getSingleResult();

      final CriteriaQuery<Foo> criteriaQuery = criteriaBuilder.createQuery(Foo.class);
      final Root<Foo> from = criteriaQuery.from(Foo.class);
      final CriteriaQuery<Foo> select = criteriaQuery.select(from);

      TypedQuery<Foo> typedQuery;
      while (pageNumber < count.intValue()) {
         typedQuery = entityManager.createQuery(select);
         typedQuery.setFirstResult(pageNumber - 1);
         typedQuery.setMaxResults(pageSize);
         LOGGER.debug("Current page: {}", typedQuery.getResultList());
         pageNumber += pageSize;
      }
   }

   // UTIL

   final int determineLastPage(final int pageSize, final long countResult) {
      return (int) (countResult / pageSize) + 1;
   }

   final void configurePagination(final Query query, final int pageNumber, final int pageSize) {
      query.setFirstResult((pageNumber - 1) * pageSize);
      query.setMaxResults(pageSize);
   }
}