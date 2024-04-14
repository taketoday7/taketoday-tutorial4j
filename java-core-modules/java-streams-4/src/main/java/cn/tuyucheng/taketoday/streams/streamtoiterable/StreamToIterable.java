package cn.tuyucheng.taketoday.streams.streamtoiterable;

import joptsimple.internal.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToIterable {
   public String streamToIterableLambda(List<String> listOfStrings) {
      Stream<String> stringStream = listOfStrings.stream();
      StringBuilder sentence = new StringBuilder();
      for (String eachString : (Iterable<String>) () -> stringStream.iterator()) {
         doSomethingOnString(eachString, sentence);
      }
      return sentence.toString();
   }

   public String streamToIterableMethodReference(List<String> listOfStrings) {
      Stream<String> stringStream = listOfStrings.stream();
      StringBuilder sentence = new StringBuilder();
      for (String eachString : (Iterable<String>) stringStream::iterator) {
         doSomethingOnString(eachString, sentence);
      }
      return sentence.toString();
   }

   public String streamToList(List<String> listOfStrings) {
      Stream<String> stringStream = listOfStrings.stream();
      StringBuilder sentence = new StringBuilder();
      for (String eachString : stringStream.collect(Collectors.toList())) {
         doSomethingOnString(eachString, sentence);
      }
      return sentence.toString();
   }

   private void doSomethingOnString(String s, StringBuilder sentence) {
      if (!Strings.isNullOrEmpty(s)) {
         sentence.append(s);
      }
   }
}
