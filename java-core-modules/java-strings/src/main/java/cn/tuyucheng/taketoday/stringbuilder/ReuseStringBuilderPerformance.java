package cn.tuyucheng.taketoday.stringbuilder;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(batchSize = 100000, iterations = 10)
@Warmup(batchSize = 100000, iterations = 10)
@State(Scope.Thread)
public class ReuseStringBuilderPerformance {

   @Benchmark
   public void benchmarkStringBuilder() {
      for (int i = 0; i < 100; i++) {
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("tuyucheng");
         stringBuilder.toString();
      }
   }

   @Benchmark
   public void benchmarkStringBuilderReuseWithSetLength() {
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < 100; i++) {
         stringBuilder.append("tuyucheng");
         stringBuilder.toString();
         stringBuilder.setLength(0);
      }
   }

   @Benchmark()
   public void benchmarkStringBuilderReuseWithDelete() {
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < 100; i++) {
         stringBuilder.append("tuyucheng");
         stringBuilder.toString();
         stringBuilder.delete(0, stringBuilder.length());
      }
   }

   public static void main(String[] args) throws Exception {
      Options options = new OptionsBuilder()
            .include(ReuseStringBuilderPerformance.class.getSimpleName()).threads(1)
            .forks(1).shouldFailOnError(true)
            .shouldDoGC(true)
            .jvmArgs("-server").build();
      new Runner(options).run();
   }

}
