package cn.tuyucheng.taketoday.jsonexception;

public class CustomException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public CustomException() {
      super("Custom exception message.");
   }
}