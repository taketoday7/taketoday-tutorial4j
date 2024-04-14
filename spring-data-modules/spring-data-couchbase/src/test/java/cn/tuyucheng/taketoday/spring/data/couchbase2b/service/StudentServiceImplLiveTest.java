package cn.tuyucheng.taketoday.spring.data.couchbase2b.service;

import cn.tuyucheng.taketoday.spring.data.couchbase.model.Student;
import cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketCouchbaseConfig;
import cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketLiveTest;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonObject;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketCouchbaseConfig.DEFAULT_BUCKET_PASSWORD;
import static cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketCouchbaseConfig.DEFAULT_BUCKET_USERNAME;
import static cn.tuyucheng.taketoday.spring.data.couchbase2b.MultiBucketCouchbaseConfig.NODE_LIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StudentServiceImplLiveTest extends MultiBucketLiveTest {

   static final String typeField = "_class";
   static final String joe = "Joe";
   static final String college = "College";
   static final String joeCollegeId = "student:" + joe + ":" + college;
   static final DateTime joeCollegeDob = DateTime.now().minusYears(21);
   static final Student joeCollege = new Student(joeCollegeId, joe, college, joeCollegeDob);
   static final JsonObject jsonJoeCollege = JsonObject.create().put(typeField, Student.class.getName()).put("firstName", joe).put("lastName", college).put("created", DateTime.now().getMillis()).put("version", 1);

   static final String judy = "Judy";
   static final String jetson = "Jetson";
   static final String judyJetsonId = "student:" + judy + ":" + jetson;
   static final DateTime judyJetsonDob = DateTime.now().minusYears(19).minusMonths(5).minusDays(3);
   static final Student judyJetson = new Student(judyJetsonId, judy, jetson, judyJetsonDob);
   static final JsonObject jsonJudyJetson = JsonObject.create().put(typeField, Student.class.getName()).put("firstName", judy).put("lastName", jetson).put("created", DateTime.now().getMillis()).put("version", 1);

   @Autowired
   StudentServiceImpl studentService;

   @BeforeAll
   static void setupBeforeClass() {
      Cluster cluster = Cluster.connect(NODE_LIST, DEFAULT_BUCKET_USERNAME, DEFAULT_BUCKET_PASSWORD);
      Bucket bucket = cluster.bucket(MultiBucketCouchbaseConfig.DEFAULT_BUCKET_NAME);
      final Collection collection = bucket.defaultCollection();
      collection.upsert(joeCollegeId, JsonObject.create().put(joeCollegeId, jsonJoeCollege));
      collection.upsert(judyJetsonId, JsonObject.create().put(judyJetsonId, jsonJudyJetson));
      cluster.disconnect();
   }

   @Test
   void whenCreatingStudent_thenDocumentIsPersisted() {
      String firstName = "Eric";
      String lastName = "Stratton";
      DateTime dateOfBirth = DateTime.now().minusYears(25);
      String id = "student:" + firstName + ":" + lastName;
      Student expectedStudent = new Student(id, firstName, lastName, dateOfBirth);
      studentService.create(expectedStudent);
      Optional<Student> actualStudent = studentService.findOne(id);
      assertTrue(actualStudent.isPresent());
      assertNotNull(actualStudent.get().getCreated());
      assertEquals(expectedStudent.getId(), actualStudent.get().getId());
   }

   // @Test(expected = ConstraintViolationException.class)
   void whenCreatingStudentWithInvalidFirstName_thenConstraintViolationException() {
      String firstName = "Er+ic";
      String lastName = "Stratton";
      DateTime dateOfBirth = DateTime.now().minusYears(25);
      String id = "student:" + firstName + ":" + lastName;
      Student student = new Student(id, firstName, lastName, dateOfBirth);
      studentService.create(student);
   }

   // @Test(expected = ConstraintViolationException.class)
   void whenCreatingStudentWithFutureDob_thenConstraintViolationException() {
      String firstName = "Jane";
      String lastName = "Doe";
      DateTime dateOfBirth = DateTime.now().plusDays(1);
      String id = "student:" + firstName + ":" + lastName;
      Student student = new Student(id, firstName, lastName, dateOfBirth);
      studentService.create(student);
   }

   @Test
   void whenFindingStudentByJohnSmithId_thenReturnsJohnSmith() {
      Optional<Student> actualStudent = studentService.findOne(joeCollegeId);
      assertTrue(actualStudent.isPresent());
      assertNotNull(actualStudent.get().getCreated());
      assertEquals(joeCollegeId, actualStudent.get().getId());
   }

   @Test
   void whenFindingAllStudents_thenReturnsTwoOrMoreStudentsIncludingJoeCollegeAndJudyJetson() {
      List<Student> resultList = studentService.findAll();
      assertNotNull(resultList);
      assertFalse(resultList.isEmpty());
      assertTrue(resultContains(resultList, joeCollege));
      assertTrue(resultContains(resultList, judyJetson));
      assertTrue(resultList.size() >= 2);
   }

   @Test
   void whenFindingByFirstNameJohn_thenReturnsOnlyStudentsNamedJohn() {
      String expectedFirstName = joe;
      List<Student> resultList = studentService.findByFirstName(expectedFirstName);
      assertNotNull(resultList);
      assertFalse(resultList.isEmpty());
      assertTrue(allResultsContainExpectedFirstName(resultList, expectedFirstName));
   }

   @Test
   void whenFindingByLastNameSmith_thenReturnsOnlyStudentsNamedSmith() {
      String expectedLastName = college;
      List<Student> resultList = studentService.findByLastName(expectedLastName);
      assertNotNull(resultList);
      assertFalse(resultList.isEmpty());
      assertTrue(allResultsContainExpectedLastName(resultList, expectedLastName));
   }

   private boolean resultContains(List<Student> resultList, Student student) {
      boolean found = false;
      for (Student p : resultList) {
         if (p.getId().equals(student.getId())) {
            found = true;
            break;
         }
      }
      return found;
   }

   private boolean allResultsContainExpectedFirstName(List<Student> resultList, String firstName) {
      boolean found = false;
      for (Student p : resultList) {
         if (p.getFirstName().equals(firstName)) {
            found = true;
            break;
         }
      }
      return found;
   }

   private boolean allResultsContainExpectedLastName(List<Student> resultList, String lastName) {
      boolean found = false;
      for (Student p : resultList) {
         if (p.getLastName().equals(lastName)) {
            found = true;
            break;
         }
      }
      return found;
   }
}