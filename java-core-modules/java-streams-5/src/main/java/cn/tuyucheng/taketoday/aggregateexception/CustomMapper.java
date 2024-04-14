package cn.tuyucheng.taketoday.aggregateexception;

import cn.tuyucheng.taketoday.aggregateexception.entity.Result;

import java.util.function.Function;

public class CustomMapper {
   public static <T, R> Function<T, Result<R, Throwable>> mapper(Function<T, R> func) {
      return arg -> {
         try {
            return new Result(func.apply(arg));
         } catch (Exception e) {
            return new Result(e);
         }
      };
   }
}
