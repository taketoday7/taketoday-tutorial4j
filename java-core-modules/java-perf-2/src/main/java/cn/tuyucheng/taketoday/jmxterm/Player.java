package cn.tuyucheng.taketoday.jmxterm;

import static cn.tuyucheng.taketoday.jmxterm.RandomNumbergenerator.generateRandomNumber;

public class Player extends AbstractPlayerMBean {
   private final String name;
   private int score = 0;

   public Player(String name) {
      super();
      this.name = name;
   }

   @Override
   public int guessNumber() {
      return generateRandomNumber();
   }

   public void incrementScore() {
      score++;
   }

   @Override
   public int getScore() {
      return score;
   }

   @Override
   public String getName() {
      return name;
   }

}
