package cn.tuyucheng.taketoday.exceptions.chainedexception.exceptions;

public class NoLeaveGrantedException extends Exception {

   public NoLeaveGrantedException(String message, Throwable cause) {
      super(message, cause);
   }

   public NoLeaveGrantedException(String message) {
      super(message);
   }
}