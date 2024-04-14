package cn.tuyucheng.taketoday.bufferedreadervsfilereader;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufferedReaderUnitTest {

   @Test
   void whenReadingAFile_thenReadsLineByLine() {
      StringBuilder result = new StringBuilder();

      final Path filePath = new File("src/test/resources/sampleText1.txt").toPath();
      try (BufferedReader br = new BufferedReader(new InputStreamReader(Files.newInputStream(filePath), StandardCharsets.UTF_8))) {
         String line;

         while ((line = br.readLine()) != null) {
            result.append(line);
            result.append('\n');
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      assertEquals("first line\nsecond line\nthird line\n", result.toString());
   }

}
