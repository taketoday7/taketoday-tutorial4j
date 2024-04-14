package cn.tuyucheng.taketoday.primitive;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

@State(Scope.Thread)
public class ByteWrapperLookup extends Lookup {
   private Byte[] elements;
   private final byte pivot = 2;

   @Override
   @Setup
   public void prepare() {
      byte common = 1;
      elements = new Byte[s];
      for (int i = 0; i < s - 1; i++) {
         elements[i] = common;
      }
      elements[s - 1] = pivot;
   }

   @Override
   @TearDown
   public void clean() {
      elements = null;
   }

   @Override
   @Benchmark
   @BenchmarkMode(Mode.AverageTime)
   public int findPosition() {
      int index = 0;
      Byte pivotWrapper = pivot;
      while (!pivotWrapper.equals(elements[index])) {
         index++;
      }
      return index;

   }

   @Override
   public String getSimpleClassName() {
      return ByteWrapperLookup.class.getSimpleName();
   }


}
