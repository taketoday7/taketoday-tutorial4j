package cn.tuyucheng.taketoday.filewriter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BufferedWriterUnitTest {

   private static final List<String> stringList = Arrays.asList("Hello", "World");

   @Test
   public void givenUsingBufferedWriter_whenStringList_thenGetTextFile() throws IOException {
      String fileName = BufferedWriterExample.generateFileFromStringList(stringList);
      long count = Files.lines(Paths.get(fileName)).count();
      assertEquals(((int) count), stringList.size(), "No. of lines in file should be equal to no. of Strings in List");
   }
}