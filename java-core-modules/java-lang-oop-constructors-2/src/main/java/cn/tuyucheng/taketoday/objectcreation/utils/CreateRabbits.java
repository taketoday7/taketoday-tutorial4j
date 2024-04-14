package cn.tuyucheng.taketoday.objectcreation.utils;

import cn.tuyucheng.taketoday.objectcreation.objects.ClonableRabbit;
import cn.tuyucheng.taketoday.objectcreation.objects.Rabbit;
import cn.tuyucheng.taketoday.objectcreation.objects.RabbitType;
import cn.tuyucheng.taketoday.objectcreation.objects.SerializableRabbit;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

public final class CreateRabbits {

   public static Rabbit createRabbitUsingNewOperator() {
      Rabbit rabbit = new Rabbit();

      return rabbit;
   }

   public static Rabbit createRabbitUsingClassForName() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
      Rabbit rabbit = (Rabbit) Class.forName("cn.tuyucheng.taketoday.objectcreation.objects.Rabbit").newInstance();

      return rabbit;
   }

   public static Rabbit createRabbitUsingClassNewInstance() throws InstantiationException, IllegalAccessException {
      Rabbit rabbit = Rabbit.class.newInstance();

      return rabbit;
   }

   public static Rabbit createRabbitUsingConstructorNewInstance() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
         InvocationTargetException, NoSuchMethodException, SecurityException {
      Rabbit rabbit = Rabbit.class.getConstructor().newInstance();

      return rabbit;
   }

   public static ClonableRabbit createRabbitUsingClone(ClonableRabbit originalRabbit) throws CloneNotSupportedException {
      ClonableRabbit clonedRabbit = (ClonableRabbit) originalRabbit.clone();

      return clonedRabbit;
   }

   public static SerializableRabbit createRabbitUsingDeserialization(File file) throws IOException, ClassNotFoundException {
      try (FileInputStream fis = new FileInputStream(file);
           ObjectInputStream ois = new ObjectInputStream(fis);) {
         return (SerializableRabbit) ois.readObject();
      }
   }

   public static Rabbit createRabbitUsingSupplier() {
      Supplier<Rabbit> rabbitSupplier = Rabbit::new;
      Rabbit rabbit = rabbitSupplier.get();

      return rabbit;
   }

   public static Rabbit[] createRabbitArray() {
      Rabbit[] rabbitArray = new Rabbit[10];

      return rabbitArray;
   }

   public static RabbitType createRabbitTypeEnum() {
      return RabbitType.PET; // any RabbitType could be returned here, PET is just an example.
   }
}
