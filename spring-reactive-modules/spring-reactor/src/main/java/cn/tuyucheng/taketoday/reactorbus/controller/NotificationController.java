package cn.tuyucheng.taketoday.reactorbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cn.tuyucheng.taketoday.reactorbus.domain.NotificationData;

import reactor.bus.Event;
import reactor.bus.EventBus;

@RestController
public class NotificationController {

   @Autowired
   private EventBus eventBus;

   @GetMapping("/startNotification/{param}")
   public void startNotification(@PathVariable Integer param) {
      for (int i = 0; i < param; i++) {
         NotificationData data = new NotificationData();
         data.setId(i);

         eventBus.notify("notificationConsumer", Event.wrap(data));

         System.out.println(STR."Notification \{i}: notification task submitted successfully");
      }
   }
}