package cn.tuyucheng.taketoday.limitrequests.client;

import cn.tuyucheng.taketoday.limitrequests.client.utils.Client;
import cn.tuyucheng.taketoday.limitrequests.client.utils.RandomConsumer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayElements {

   private DelayElements() {
   }

   public static Flux<Integer> fetch(WebClient client, int requests, int delay) {
      String clientId = Client.id(requests, DelayElements.class, delay);

      return Flux.range(1, requests)
            .log()
            .delayElements(Duration.ofMillis(delay))
            .flatMap(_ -> RandomConsumer.get(client, clientId));
   }

   public static void main(String[] args) {
      String baseUrl = args[0];
      WebClient client = WebClient.create(baseUrl);

      fetch(client, 12, 1050).doOnNext(System.out::println)
            .blockLast();
   }
}