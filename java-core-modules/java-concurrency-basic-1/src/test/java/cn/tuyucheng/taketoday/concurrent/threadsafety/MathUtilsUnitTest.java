package cn.tuyucheng.taketoday.concurrent.threadsafety;

import cn.tuyucheng.taketoday.concurrent.threadsafety.mathutils.MathUtils;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class MathUtilsUnitTest {

   @Test
   public void whenCalledFactorialMethod_thenCorrect() {
      assertThat(MathUtils.factorial(2)).isEqualTo(new BigInteger("2"));
   }
}
