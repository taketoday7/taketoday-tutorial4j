package cn.tuyucheng.taketoday.streams.multiplefilters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;

public class MultipleFiltersVsComplexConditionReadabilityUnitTest {

   private List<Student> students;
   private Student mathStudent;

   @BeforeEach
   public void beforeEach() {
      this.mathStudent = new Student();
      mathStudent.setName("John Doe");
      mathStudent.setYear(3);
      mathStudent.setProfile(Student.Profile.MATH);
      mathStudent.setMarks(List.of(80, 90, 77, 95, 100));

      Student csStudent = new Student();
      csStudent.setName("Paul Atreides");
      csStudent.setYear(2);
      csStudent.setProfile(Student.Profile.COMPUTER_SCIENCE);
      csStudent.setMarks(List.of(30, 40, 60));

      this.students = List.of(mathStudent, csStudent);
   }

   @Test
   public void whenUsingMultipleFilters_dataShouldBeFiltered() {
      List<Student> filteredStream = students.stream()
            .filter(s -> s.getMarksAverage() > 50)
            .filter(s -> s.getMarks().size() > 3)
            .filter(not(s -> s.getProfile() == Student.Profile.PHYSICS))
            .collect(Collectors.toList());

      assertThat(filteredStream).containsExactly(mathStudent);
   }

   @Test
   public void whenUsingSingleComplexFilter_dataShouldBeFiltered() {
      List<Student> filteredStream = students.stream()
            .filter(s -> s.getMarksAverage() > 50
                  && s.getMarks().size() > 3
                  && s.getProfile() != Student.Profile.PHYSICS)
            .collect(Collectors.toList());

      assertThat(filteredStream).containsExactly(mathStudent);
   }

   @Test
   public void whenUsingSingleComplexFilterExtracted_dataShouldBeFiltered() {
      List<Student> filteredStream = students.stream()
            .filter(Student::isEligibleForScholarship)
            .collect(Collectors.toList());

      assertThat(filteredStream).containsExactly(mathStudent);
   }

}