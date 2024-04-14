package cn.tuyucheng.taketoday.hamcrest;

public class Person {
   String name;
   String address;

   public Person(String personName, String personAddress) {
      name = personName;
      address = personAddress;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   @Override
   public String toString() {
      return "[address:" + address + ",name:" + name + "]";
   }
}