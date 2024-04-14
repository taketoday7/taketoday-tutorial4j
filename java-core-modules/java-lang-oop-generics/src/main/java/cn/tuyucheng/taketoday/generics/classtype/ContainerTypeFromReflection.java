package cn.tuyucheng.taketoday.generics.classtype;

public class ContainerTypeFromReflection<T> {
   private T content;

   public ContainerTypeFromReflection(T content) {
      this.content = content;
   }

   public Class<?> getClazz() {
      return this.content.getClass();
   }
}