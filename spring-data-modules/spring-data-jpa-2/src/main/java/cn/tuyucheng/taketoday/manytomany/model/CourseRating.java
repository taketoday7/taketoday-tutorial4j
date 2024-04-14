package cn.tuyucheng.taketoday.manytomany.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_rating")
public class CourseRating {

   @EmbeddedId
   private CourseRatingKey id;

   @ManyToOne
   @MapsId("studentId")
   @JoinColumn(name = "student_id")
   private Student student;

   @ManyToOne
   @MapsId("courseId")
   @JoinColumn(name = "course_id")
   private Course course;

   @Column(name = "rating")
   private int rating;

   public CourseRating() {
   }

   public CourseRatingKey getId() {
      return id;
   }

   public void setId(CourseRatingKey id) {
      this.id = id;
   }

   public Student getStudent() {
      return student;
   }

   public void setStudent(Student student) {
      this.student = student;
   }

   public Course getCourse() {
      return course;
   }

   public void setCourse(Course course) {
      this.course = course;
   }

   public int getRating() {
      return rating;
   }

   public void setRating(int rating) {
      this.rating = rating;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      CourseRating other = (CourseRating) obj;
      if (id == null) {
         return other.id == null;
      } else return id.equals(other.id);
   }
}