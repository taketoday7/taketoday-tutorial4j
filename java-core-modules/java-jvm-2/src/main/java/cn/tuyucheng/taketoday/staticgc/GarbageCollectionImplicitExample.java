package cn.tuyucheng.taketoday.staticgc;

public class GarbageCollectionImplicitExample {

   public static final String METHOD_NAME = "printValue";

   public static void main(String[] args) {
      while (true) {
         loadClass();
      }
   }


   /**
    * The method loads a class and creates its instance. After the invocation of this method all the local variables go outside the scope.
    */
   private static void loadClass() {
      try {
         final String className = "cn.tuyucheng.taketoday.classloader.GarbageCollectedStaticFieldHolder";
         CustomClassloader loader = new CustomClassloader(GarbageCollectionImplicitExample.class.getClassLoader());
         Class<?> clazz = loader.loadClass(className);
         Object instance = clazz.getConstructor().newInstance();
         clazz.getMethod(METHOD_NAME).invoke(instance);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
