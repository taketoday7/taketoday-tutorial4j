package cn.tuyucheng.taketoday.multibeaninstantiation.solution1;

public class Person {
   private String firstName;
   private String lastName;

   public Person(String firstName, String secondName) {
      super();
      this.firstName = firstName;
      this.lastName = secondName;
   }

   @Override
   public String toString() {
      return STR."Person [firstName=\{firstName}, secondName=\{lastName}]";
   }
}