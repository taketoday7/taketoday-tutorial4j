package cn.tuyucheng.taketoday.spring.data.couchbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class StudentTemplateServiceLiveTest extends StudentServiceLiveTest {

   @Autowired
   @Qualifier("StudentTemplateService")
   void setStudentService(StudentService service) {
      this.studentService = service;
   }
}
