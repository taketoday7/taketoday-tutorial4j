package cn.tuyucheng.taketoday.exceptions.throwvsthrows;

public class DataAccessException extends RuntimeException {

   public DataAccessException(String message, Throwable cause) {
      super(message, cause);
   }

}
