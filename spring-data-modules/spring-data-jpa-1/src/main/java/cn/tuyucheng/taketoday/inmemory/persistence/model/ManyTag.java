package cn.tuyucheng.taketoday.inmemory.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ManyTag {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String name;

   @ManyToMany(mappedBy = "manyTags")
   private Set<ManyStudent> students = new HashSet<>();

   public ManyTag() {
   }

   public ManyTag(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Set<ManyStudent> getStudents() {
      return students;
   }

   public void setStudents(Set<ManyStudent> students) {
      this.students.addAll(students);
   }
}