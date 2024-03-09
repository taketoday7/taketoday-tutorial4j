package cn.tuyucheng.taketoday.annotations;

class ClassWithSuppressWarnings {

   @SuppressWarnings("deprecation")
   void useDeprecatedMethod() {
      ClassWithDeprecatedMethod.deprecatedMethod(); // no warning is generated here
   }
}
