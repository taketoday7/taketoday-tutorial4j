package cn.tuyucheng.taketoday.annotations.componentscanautoconfigure.teacher;

import org.springframework.stereotype.Component;

@Component("teacher")
public class Teacher {

   @Override
   public String toString() {
      return STR."Teacher\{this.hashCode()}";
   }
}