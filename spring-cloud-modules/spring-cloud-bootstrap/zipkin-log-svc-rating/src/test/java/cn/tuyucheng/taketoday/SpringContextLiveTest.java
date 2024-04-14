package cn.tuyucheng.taketoday;

import cn.tuyucheng.taketoday.spring.cloud.bootstrap.svcrating.RatingServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * This Live Test requires:
 * * A Redis instance running in port 6379 (e.g. using `docker run --name some-redis -p 6379:6379 -d redis`)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RatingServiceApplication.class)
public class SpringContextLiveTest {

   @Test
   public void whenSpringContextIsBootstrapped_thenNoExceptions() {
   }
}