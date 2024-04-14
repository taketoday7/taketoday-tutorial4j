package cn.tuyucheng.taketoday.staticsingletondifference;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.stream.IntStream;

public class ForSingletonsUnitTest {

   @Test
   public void whenStaticUtilClassInheritance_thenOverridingFails() {
      SuperUtility superUtility = new SubUtility();
      Assertions.assertNotEquals("ECHO", superUtility.echoIt("ECHO"));
      Assertions.assertEquals("SUPER", superUtility.echoIt("ECHO"));
   }

   @Test
   public void whenSingletonDerivesBaseClass_thenRuntimePolymorphism() {
      MyLock myLock = new MyLock();
      Assertions.assertEquals("Taken Specific Lock", myLock.takeLock(10));
      myLock = SingletonLock.getInstance();
      Assertions.assertEquals("Taken Singleton Lock", myLock.takeLock(10));
   }

   @Test
   public void whenSingletonImplementsInterface_thenRuntimePolymorphism() {
      SingletonInterface singleton = FileSystemSingleton.getInstance();
      Assertions.assertEquals("File System Responsibilities", singleton.describeMe());
      singleton = CachingSingleton.getInstance();
      Assertions.assertEquals("Caching Responsibilities", singleton.describeMe());
   }

   @Test
   public void whenSingleton_thenPassAsArguments() {
      SingletonInterface singleton = FileSystemSingleton.getInstance();
      Assertions.assertEquals("Taken Singleton Lock", singleton.passOnLocks(SingletonLock.getInstance()));
   }

   @Test
   public void whenSingleton_thenAllowState() {
      SingletonInterface singleton = FileSystemSingleton.getInstance();
      IntStream.range(0, 5)
            .forEach(i -> singleton.increment());
      Assertions.assertEquals(5, ((FileSystemSingleton) singleton).getFilesWritten());
   }

   @Test
   public void whenSingleton_thenAllowSerializationDeserialization() {
      SingletonInterface singleton = SerializableCloneableSingleton.getInstance();
      singleton.increment();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
         new ObjectOutputStream(baos).writeObject(singleton);
         SerializableCloneableSingleton singletonNew = (SerializableCloneableSingleton) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
         Assertions.assertEquals(1, singletonNew.getState());
         Assertions.assertEquals(singleton.hashCode(), singletonNew.hashCode());
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
   }

   @Test
   public void whenSingleton_thenAllowCloneable() {
      SerializableCloneableSingleton singleton = SerializableCloneableSingleton.getInstance();
      singleton.increment();
      try {
         Assertions.assertEquals(2, ((SerializableCloneableSingleton) singleton.cloneObject()).getState());
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
   }
}