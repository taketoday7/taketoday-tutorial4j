package cn.tuyucheng.taketoday.fileparser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ScannerIntUnitTest {

   protected static final String NUMBER_FILENAME = "src/test/resources/sampleNumberFile.txt";

   @Test
   public void whenParsingExistingTextFile_thenGetIntArrayList() throws IOException {
      List<Integer> numbers = ScannerIntExample.generateArrayListFromFile(NUMBER_FILENAME);
      assertTrue("File does not has 2 lines", numbers.size() == 2);
   }
}
