package cn.tuyucheng.taketoday.multiline;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiLineStringUnitTest {


   @Test
   public void whenCompareMultiLineStrings_thenTheyAreAllTheSame() throws IOException {
      MultiLineString ms = new MultiLineString();
      assertEquals(ms.stringConcatenation(), ms.stringJoin());
      assertEquals(ms.stringJoin(), ms.stringBuilder());
      assertEquals(ms.stringBuilder(), ms.guavaJoiner());
      assertEquals(ms.guavaJoiner(), ms.loadFromFile());
      assertEquals(ms.loadFromFile(), ms.textBlocks());
   }

}
