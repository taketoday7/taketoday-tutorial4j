package cn.tuyucheng.taketoday.file.content.comparison;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CompareByMemoryMappedFilesUnitTest {

   public static Path path1 = null;
   public static Path path2 = null;

   @BeforeAll
   public static void setup() throws IOException {

      path1 = Files.createTempFile("file1Test", ".txt");
      path2 = Files.createTempFile("file2Test", ".txt");
   }

   @Test
   public void whenFilesIdentical_thenReturnTrue() throws IOException {

      Files.writeString(path1, "testing line 1" + System.lineSeparator() + "line 2");
      Files.writeString(path2, "testing line 1" + System.lineSeparator() + "line 2");

      Assertions.assertTrue(CompareFileContents.compareByMemoryMappedFiles(path1, path2));
   }

   @Test
   public void whenFilesDifferent_thenReturnFalse() throws IOException {

      Files.writeString(path1, "testing line " + System.lineSeparator() + "line 2");
      Files.writeString(path2, "testing line 1" + System.lineSeparator() + "line 2");

      assertFalse(CompareFileContents.compareByMemoryMappedFiles(path1, path2));
   }
}
