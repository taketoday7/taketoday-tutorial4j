package cn.tuyucheng.taketoday.persistence.audit;

import cn.tuyucheng.taketoday.persistence.model.Bar;
import cn.tuyucheng.taketoday.persistence.model.Foo;
import cn.tuyucheng.taketoday.persistence.service.IBarAuditableService;
import cn.tuyucheng.taketoday.persistence.service.IFooAuditableService;
import cn.tuyucheng.taketoday.spring.config.PersistenceTestConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersistenceTestConfig.class}, loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class EnversFooBarAuditIntegrationTest {

   private static final Logger LOGGER = LoggerFactory.getLogger(EnversFooBarAuditIntegrationTest.class);

   @Autowired
   @Qualifier("fooHibernateAuditableService")
   private IFooAuditableService fooService;

   @Autowired
   @Qualifier("barHibernateAuditableService")
   private IBarAuditableService barService;

   @Autowired
   private SessionFactory sessionFactory;

   private Session session;

   @BeforeEach
   void setUp() {
      LOGGER.info("setUp()");
      makeRevisions();
      session = sessionFactory.openSession();
   }

   @AfterEach
   void tearDown() {
      LOGGER.info("tearDown()");
      session.close();
   }

   private void makeRevisions() {
      final Bar bar = rev1();
      rev2(bar);
      rev3(bar);
      rev4(bar);
   }

   // REV #1: insert BAR & FOO1
   private Bar rev1() {
      final Bar bar = new Bar("BAR");
      final Foo foo1 = new Foo("FOO1");
      foo1.setBar(bar);
      fooService.create(foo1);
      return bar;
   }

   // REV #2: insert FOO2 & update BAR
   private void rev2(final Bar bar) {
      final Foo foo2 = new Foo("FOO2");
      foo2.setBar(bar);
      fooService.create(foo2);
   }

   // REV #3: update BAR
   private void rev3(final Bar bar) {
      bar.setName("BAR1");
      barService.update(bar);
   }

   // REV #4: insert FOO3 & update BAR
   private void rev4(final Bar bar) {
      final Foo foo3 = new Foo("FOO3");
      foo3.setBar(bar);
      fooService.create(foo3);
   }

   @Test
   final void whenFooBarsModified_thenFooBarsAudited() {
      List<Bar> barRevisionList;
      List<Foo> fooRevisionList;

      // test Bar revisions

      barRevisionList = barService.getRevisions();

      assertNotNull(barRevisionList);
      assertEquals(4, barRevisionList.size());

      assertEquals("BAR", barRevisionList.get(0).getName());
      assertEquals("BAR", barRevisionList.get(1).getName());
      assertEquals("BAR1", barRevisionList.get(2).getName());
      assertEquals("BAR1", barRevisionList.get(3).getName());

      assertEquals(1, barRevisionList.get(0).getFooSet().size());
      assertEquals(2, barRevisionList.get(1).getFooSet().size());
      assertEquals(2, barRevisionList.get(2).getFooSet().size());
      assertEquals(3, barRevisionList.get(3).getFooSet().size());

      // test Foo revisions

      fooRevisionList = fooService.getRevisions();
      assertNotNull(fooRevisionList);
      assertEquals(3, fooRevisionList.size());
      assertEquals("FOO1", fooRevisionList.get(0).getName());
      assertEquals("FOO2", fooRevisionList.get(1).getName());
      assertEquals("FOO3", fooRevisionList.get(2).getName());
   }
}