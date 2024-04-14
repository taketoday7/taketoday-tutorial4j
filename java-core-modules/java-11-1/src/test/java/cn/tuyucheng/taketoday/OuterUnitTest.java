package cn.tuyucheng.taketoday;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OuterUnitTest {

   private static final String NEST_HOST_NAME = "cn.tuyucheng.taketoday.Outer";

   @Test
   public void whenGetNestHostFromOuter_thenGetNestHost() {
      is(Outer.class.getNestHost().getName()).equals(NEST_HOST_NAME);
   }

   @Test
   public void whenGetNestHostFromInner_thenGetNestHost() {
      is(Outer.Inner.class.getNestHost().getName()).equals(NEST_HOST_NAME);
   }

   @Test
   public void whenCheckNestmatesForNestedClasses_thenGetTrue() {
      is(Outer.Inner.class.isNestmateOf(Outer.class)).equals(true);
   }

   @Test
   public void whenCheckNestmatesForUnrelatedClasses_thenGetFalse() {
      is(Outer.Inner.class.isNestmateOf(Outer.class)).equals(false);
   }

   @Test
   public void whenGetNestMembersForNestedClasses_thenGetAllNestedClasses() {
      Set<String> nestMembers = Arrays.stream(Outer.Inner.class.getNestMembers())
            .map(Class::getName)
            .collect(Collectors.toSet());

      is(nestMembers.size()).equals(2);

      assertTrue(nestMembers.contains("cn.tuyucheng.taketoday.Outer"));
      assertTrue(nestMembers.contains("cn.tuyucheng.taketoday.Outer$Inner"));
   }
}