package cn.tuyucheng.taketoday.annotations.componentscanautoconfigure.student;

import org.springframework.stereotype.Component;

@Component("student")
public class Student {

   @Override
   public String toString() {
      return STR."Student\{this.hashCode()}";
   }
}