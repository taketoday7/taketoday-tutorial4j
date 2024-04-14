package cn.tuyucheng.taketoday.suppliercallable.service.callable;

import cn.tuyucheng.taketoday.suppliercallable.data.User;
import cn.tuyucheng.taketoday.suppliercallable.service.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableServiceImpl implements Service {

   @Override
   public User execute(User user) {
      ExecutorService executorService = Executors.newCachedThreadPool();

      try {
         Future<Integer> ageFuture = executorService.submit(new AgeCalculatorCallable(user.getBirthDate()));
         Integer age = ageFuture.get();
         Future<Boolean> canDriveACarFuture = executorService.submit(new CarDriverValidatorCallable(age));
         Boolean canDriveACar = canDriveACarFuture.get();
         user.setAge(age);
         user.setCanDriveACar(canDriveACar);
      } catch (ExecutionException | InterruptedException e) {
         throw new RuntimeException(e.getCause());
      }

      return user;
   }

}
