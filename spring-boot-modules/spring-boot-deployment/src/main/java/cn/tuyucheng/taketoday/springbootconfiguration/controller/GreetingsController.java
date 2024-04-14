package cn.tuyucheng.taketoday.springbootconfiguration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

   @GetMapping("/greetings/{username}")
   public String getGreetings(@PathVariable("username") String userName) {
      return STR."Hello \{userName}, Good day...!!!";
   }
}