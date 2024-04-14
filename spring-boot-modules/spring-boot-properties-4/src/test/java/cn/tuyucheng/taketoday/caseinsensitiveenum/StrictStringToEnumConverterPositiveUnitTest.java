package cn.tuyucheng.taketoday.caseinsensitiveenum;

import cn.tuyucheng.taketoday.caseinsensitiveenum.StrictStringToEnumConverterNegativeUnitTest.WeekDayConverterConfiguration;
import cn.tuyucheng.taketoday.caseinsensitiveenum.converter.StrictNullableWeekDayConverter;
import cn.tuyucheng.taketoday.caseinsensitiveenum.providers.WeekDayHolderArgumentsProvider;
import cn.tuyucheng.taketoday.caseinsensitiveenum.week.WeekDays;
import cn.tuyucheng.taketoday.caseinsensitiveenum.week.WeekDaysHolder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
      "monday=MONDAY",
      "tuesday=TUESDAY",
      "wednesday=WEDNESDAY",
      "thursday=THURSDAY",
      "friday=FRIDAY",
      "saturday=SATURDAY",
      "sunday=SUNDAY",
}, classes = {WeekDaysHolder.class, WeekDayConverterConfiguration.class})
class StrictStringToEnumConverterPositiveUnitTest {

   public static class WeekDayConverterConfiguration {
      @Bean
      public ConversionService conversionService() {
         final DefaultConversionService defaultConversionService = new DefaultConversionService();
         defaultConversionService.addConverter(new StrictNullableWeekDayConverter());
         return defaultConversionService;
      }
   }

   @Autowired
   private WeekDaysHolder holder;

   @ParameterizedTest
   @ArgumentsSource(WeekDayHolderArgumentsProvider.class)
   void givenPropertiesWhenInjectEnumThenValueIsNull(
         Function<WeekDaysHolder, WeekDays> methodReference, WeekDays expected) {
      WeekDays actual = methodReference.apply(holder);
      assertThat(actual).isEqualTo(expected);
   }
}