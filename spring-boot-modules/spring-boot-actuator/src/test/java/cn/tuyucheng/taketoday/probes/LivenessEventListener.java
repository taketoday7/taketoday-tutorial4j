package cn.tuyucheng.taketoday.probes;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.context.event.EventListener;

public class LivenessEventListener {

   @EventListener
   public void onEvent(AvailabilityChangeEvent<LivenessState> event) {
      switch (event.getState()) {
         case BROKEN -> {
         }
         // notify others
         case CORRECT -> {
         }
         // we're back
      }
   }
}