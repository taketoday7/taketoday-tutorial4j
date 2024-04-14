package cn.tuyucheng.taketoday.reactive.serversentevents.server.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;

@RestController
@RequestMapping("/sse-server")
public class ServerController {

   @GetMapping("/stream-sse")
   public Flux<ServerSentEvent<String>> streamEvents() {
      return Flux.interval(Duration.ofSeconds(1))
            .map(sequence -> ServerSentEvent.<String>builder()
                  .id(String.valueOf(sequence))
                  .event("periodic-event")
                  .data(STR."SSE - \{LocalTime.now()}")
                  .build());
   }

   @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   public Flux<String> streamFlux() {
      return Flux.interval(Duration.ofSeconds(1))
            .map(_ -> STR."Flux - \{LocalTime.now()}");
   }
}