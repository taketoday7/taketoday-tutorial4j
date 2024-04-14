package cn.tuyucheng.taketoday.listofobjectstolistofstring;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConvertObjectListToStringListUnitTest {

   @Test
   public void givenObjectList_whenForEachUsedToConvert_thenReturnSuccess() {
      List<String> outputList = new ArrayList<>(objectListWithNull().size());
      for (Object obj : objectListWithNull()) {
         outputList.add(Objects.toString(obj, null));
      }
      Assertions.assertEquals(expectedStringListWithNull(), outputList);
   }

   @Test
   public void givenObjectList_whenUsingStreamsToConvert_thenReturnSuccess() {
      List<String> outputList;
      outputList = objectListWithNull().stream()
            .map((obj) -> Objects.toString(obj, null))
            .collect(Collectors.toList());
      Assertions.assertEquals(expectedStringListWithNull(), outputList);

   }

   @Test
   public void givenObjectList_whenUsingStreamsUnmodifiableListToConvert_thenReturnSuccess() {
      List<String> outputList;
      outputList = objectListWithNull().stream()
            .filter(Objects::nonNull)
            .map((obj) -> Objects.toString(obj, null))
            .collect(Collectors.toUnmodifiableList());
      Assertions.assertEquals(expectedStringListWithoutNull(), outputList);

   }

   @Test
   public void givenObjectList_whenUsingGuavaTransform_thenReturnSuccess() {
      List<String> outputList;
      outputList = Lists.transform(objectListWithNull(), obj -> Objects.toString(obj, null));
      Assertions.assertEquals(expectedStringListWithNull(), outputList);
   }

   @Test
   public void givenObjectListWithNoNull_whenUsingToList_thenReturnSuccess() {
      List<String> outputList;
      outputList = objectListWithoutNull().stream()
            .map((obj) -> Objects.toString(obj, null))
            .toList();
      Assertions.assertEquals(expectedStringListWithoutNull(), outputList);
   }

   private List<String> expectedStringListWithNull() {
      List<String> listOfStrings = new ArrayList<>();
      listOfStrings.add("1");
      listOfStrings.add("true");
      listOfStrings.add("hello");
      listOfStrings.add(Double.toString(273773.98));
      listOfStrings.add(null);
      listOfStrings.add(new Node(2, 4).toString());
      listOfStrings.add(new User("John Doe").toString());
      return listOfStrings;
   }

   private List<Object> objectListWithNull() {
      List<Object> listOfStrings = new ArrayList<>();
      listOfStrings.add(1);
      listOfStrings.add(true);
      listOfStrings.add("hello");
      listOfStrings.add(Double.valueOf(273773.98));
      listOfStrings.add(null);
      listOfStrings.add(new Node(2, 4));
      listOfStrings.add(new User("John Doe"));
      return listOfStrings;
   }

   private List<String> expectedStringListWithoutNull() {
      return List.of("1", "true", "hello", Double.toString(273773.98), new Node(2, 4).toString(), new User("John Doe").toString());
   }

   private List<Object> objectListWithoutNull() {
      return List.of(1, true, "hello", Double.valueOf(273773.98), new Node(2, 4), new User("John Doe"));
   }
}
