package cn.tuyucheng.taketoday.jcommander.helloworld;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class HelloWorldApp {

   /*
    * Execute:
    * mvn exec:java -Dexec.mainClass=cn.tuyucheng.taketoday.jcommander.helloworld.HelloWorldApp -q \
    *   -Dexec.args="--name JavaWorld"
    */
   public static void main(String[] args) {
      HelloWorldArgs jArgs = new HelloWorldArgs();
      JCommander helloCmd = JCommander
            .newBuilder()
            .addObject(jArgs)
            .build();

      helloCmd.parse(args);
      System.out.println("Hello " + jArgs.getName());
   }
}

class HelloWorldArgs {

   @Parameter(
         names = "--name",
         description = "User name",
         required = true
   )
   private String name;

   public String getName() {
      return name;
   }
}
