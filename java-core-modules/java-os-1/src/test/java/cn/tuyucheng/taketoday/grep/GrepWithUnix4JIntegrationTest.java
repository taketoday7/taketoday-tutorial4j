package cn.tuyucheng.taketoday.grep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.unix4j.Unix4j;
import org.unix4j.line.Line;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.unix4j.Unix4j.grep;
import static org.unix4j.unix.Grep.Options;
import static org.unix4j.unix.cut.CutOption.fields;

public class GrepWithUnix4JIntegrationTest {

   private File fileToGrep;

   @BeforeEach
   public void init() {
      final String separator = File.separator;
      final String filePath = String.join(separator, new String[]{"src", "test", "resources", "dictionary.in"});
      fileToGrep = new File(filePath);
   }

   @Test
   public void whenGrepWithSimpleString_thenCorrect() {
      int expectedLineCount = 5;

      // grep "NINETEEN" dictionary.in
      List<Line> lines = Unix4j.grep("NINETEEN", fileToGrep).toLineList();

      assertEquals(expectedLineCount, lines.size());
   }

   @Test
   public void whenInverseGrepWithSimpleString_thenCorrect() {
      int expectedLineCount = 8;

      // grep -v "NINETEEN" dictionary.in
      List<Line> lines = grep(Options.v, "NINETEEN", fileToGrep).toLineList();

      assertEquals(expectedLineCount, lines.size());
   }

   @Test
   public void whenGrepWithRegex_thenCorrect() {
      int expectedLineCount = 5;

      // grep -c ".*?NINE.*?" dictionary.in
      String patternCount = grep(Options.c, ".*?NINE.*?", fileToGrep).cut(fields, ":", 1).toStringResult();

      assertEquals(expectedLineCount, Integer.parseInt(patternCount));
   }
}
