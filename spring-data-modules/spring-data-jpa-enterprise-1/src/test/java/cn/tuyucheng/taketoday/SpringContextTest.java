package cn.tuyucheng.taketoday;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cn.tuyucheng.taketoday.boot.Application;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class SpringContextTest {

   @Test
   void whenSpringContextIsBootstrapped_thenNoExceptions() {
   }
}