package cn.tuyucheng.taketoday.mdc;

import static java.lang.Math.floor;
import static java.lang.Math.random;

public class TransactionFactory {

   private static final String[] NAMES = {"John", "Susan", "Marc", "Samantha"};
   private static long nextId = 1;

   public Transfer newInstance() {
      String transactionId = String.valueOf(nextId++);
      String owner = NAMES[(int) floor(random() * NAMES.length)];
      long amount = (long) (random() * 1500 + 500);
      return new Transfer(transactionId, owner, amount);
   }
}