package cn.tuyucheng.taketoday.externalizable;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExternalizableUnitTest {

   private final static String OUTPUT_FILE_NAME = "externalizable.txt";

   @Rule
   public TemporaryFolder tempFolder = new TemporaryFolder();

   private File outputFile;

   @BeforeEach
   public void setUp() throws Exception {
      outputFile = tempFolder.newFile(OUTPUT_FILE_NAME);
   }

   @Test
   public void whenSerializing_thenUseExternalizable() throws IOException, ClassNotFoundException {

      Country c = new Country();
      c.setCapital("Yerevan");
      c.setCode(374);
      c.setName("Armenia");

      FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      c.writeExternal(objectOutputStream);

      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();

      FileInputStream fileInputStream = new FileInputStream(outputFile);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      Country c2 = new Country();
      c2.readExternal(objectInputStream);

      objectInputStream.close();
      fileInputStream.close();

      assertEquals(c2.getCode(), c.getCode());
      assertEquals(c2.getName(), c.getName());
   }

   @Test
   public void whenInheritanceSerialization_then_UseExternalizable() throws IOException, ClassNotFoundException {

      Region r = new Region();
      r.setCapital("Yerevan");
      r.setCode(374);
      r.setName("Armenia");
      r.setClimate("Mediterranean");
      r.setPopulation(120.000);

      FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      r.writeExternal(objectOutputStream);

      objectOutputStream.flush();
      objectOutputStream.close();
      fileOutputStream.close();

      FileInputStream fileInputStream = new FileInputStream(outputFile);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

      Region r2 = new Region();
      r2.readExternal(objectInputStream);

      objectInputStream.close();
      fileInputStream.close();

      assertNull(r2.getPopulation());
   }
}
