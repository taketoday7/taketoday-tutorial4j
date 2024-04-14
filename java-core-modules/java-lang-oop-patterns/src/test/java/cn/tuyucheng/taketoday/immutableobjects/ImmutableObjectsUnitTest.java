package cn.tuyucheng.taketoday.immutableobjects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImmutableObjectsUnitTest {

   @Test
   public void whenCallingStringReplace_thenStringDoesNotMutate() {
      // 2. What's an Immutable Object?
      final String name = "tuyucheng";
      final String newName = name.replace("dung", "----");

      assertEquals("tuyucheng", name);
      assertEquals("tuyu----", newName);
   }

   public void whenReassignFinalValue_thenCompilerError() {
      // 3. The final Keyword in Java (1)
      final String name = "tuyucheng";
      // name = "tuyu...";
   }

   @Test
   public void whenAddingElementToList_thenSizeChange() {
      // 3. The final Keyword in Java (2)
      final List<String> strings = new ArrayList<>();
      assertEquals(0, strings.size());
      strings.add("tuyucheng");
      assertEquals(1, strings.size());
   }
}
