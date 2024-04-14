package cn.tuyucheng.taketoday.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class AsyncComponent {

   @Async
   public void asyncMethodWithVoidReturnType() {
      System.out.println(STR."Execute method asynchronously. \{Thread.currentThread().getName()}");
   }

   @Async
   public Future<String> asyncMethodWithReturnType() {
      System.out.println(STR."Execute method asynchronously \{Thread.currentThread().getName()}");
      try {
         Thread.sleep(5000);
         return new AsyncResult<>("hello world !!!!");
      } catch (final InterruptedException _) {
      }

      return null;
   }

   @Async("threadPoolTaskExecutor")
   public void asyncMethodWithConfiguredExecutor() {
      System.out.println(STR."Execute method asynchronously with configured executor\{Thread.currentThread().getName()}");
   }

   @Async
   public void asyncMethodWithExceptions() throws Exception {
      throw new Exception("Throw message from asynchronous method. ");
   }
}