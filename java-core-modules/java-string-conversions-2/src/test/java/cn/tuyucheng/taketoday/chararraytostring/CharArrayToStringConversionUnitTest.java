package cn.tuyucheng.taketoday.chararraytostring;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CharArrayToStringConversionUnitTest {

   @Test
   public void whenStringConstructor_thenOK() {
      final char[] charArray = {'b', 'a', 'e', 'l', 'd', 'u', 'n', 'g'};

      String string = new String(charArray);

      assertThat(string, is("tuyucheng"));
   }

   @Test
   public void whenStringCopyValueOf_thenOK() {
      final char[] charArray = {'b', 'a', 'e', 'l', 'd', 'u', 'n', 'g'};

      String string = String.copyValueOf(charArray);

      assertThat(string, is("tuyucheng"));
   }

   @Test
   public void whenStringValueOf_thenOK() {
      final char[] charArray = {'b', 'a', 'e', 'l', 'd', 'u', 'n', 'g'};

      String string = String.valueOf(charArray);

      assertThat(string, is("tuyucheng"));
   }

   @Test
   public void whenStringBuilder_thenOK() {
      final char[][] arrayOfCharArray = {{'b', 'a'}, {'e', 'l', 'd', 'u'}, {'n', 'g'}};

      StringBuilder sb = new StringBuilder();
      for (char[] subArray : arrayOfCharArray) {
         sb.append(subArray);
      }

      assertThat(sb.toString(), is("tuyucheng"));
   }

   @Test
   public void whenStreamCollectors_thenOK() {
      final Character[] charArray = {'b', 'a', 'e', 'l', 'd', 'u', 'n', 'g'};

      Stream<Character> charStream = Arrays.stream(charArray);
      String string = charStream.map(String::valueOf).collect(Collectors.joining());

      assertThat(string, is("tuyucheng"));
   }

   @Test
   public void whenGoogleCommonBaseJoiners_thenOK() {
      final Character[] charArray = {'b', 'a', 'e', 'l', 'd', 'u', 'n', 'g'};

      String string = Joiner.on("|").join(charArray);

      assertThat(string, is("b|a|e|l|d|u|n|g"));
   }
}
