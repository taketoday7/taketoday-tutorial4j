package cn.tuyucheng.taketoday.compare;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Component
public class StartupEventHandler {

   private static final Logger logger = LoggerFactory.getLogger(StartupEventHandler.class);

   public StartupEventHandler(MeterRegistry registry) {
      this.meterRegistry = registry;
   }

   private final String[] METRICS = {
         "jvm.memory.used",
         "jvm.classes.loaded",
         "jvm.threads.live"};

   private String METRIC_MSG_FORMAT = "Startup Metric >> {}={}";

   private final MeterRegistry meterRegistry;

   @EventListener
   public void getAndLogStartupMetrics(ApplicationReadyEvent event) {
      Arrays.asList(METRICS)
            .forEach(this::getAndLogActuatorMetric);
   }

   private void getAndLogActuatorMetric(String metric) {
      Meter meter = meterRegistry.find(metric).meter();
      Map<Statistic, Double> stats = getSamples(meter);

      logger.info(METRIC_MSG_FORMAT, metric, stats.get(Statistic.VALUE).longValue());
   }

   private Map<Statistic, Double> getSamples(Meter meter) {
      Map<Statistic, Double> samples = new LinkedHashMap<>();
      mergeMeasurements(samples, meter);
      return samples;
   }

   private void mergeMeasurements(Map<Statistic, Double> samples, Meter meter) {
      meter.measure().forEach(measurement ->
            samples.merge(measurement.getStatistic(), measurement.getValue(), mergeFunction(measurement.getStatistic())));
   }

   private BiFunction<Double, Double, Double> mergeFunction(Statistic statistic) {
      return Statistic.MAX.equals(statistic) ? Double::max : Double::sum;
   }
}