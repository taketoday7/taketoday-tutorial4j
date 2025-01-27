package cn.tuyucheng.taketoday.webflux;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;

public class EmployeeWebSocketClient {

   public static void main(String[] args) {

      WebSocketClient client = new ReactorNettyWebSocketClient();

      client.execute(URI.create("ws://localhost:8080/employee-feed"), session -> session.receive()
                  .map(WebSocketMessage::getPayloadAsText)
                  .doOnNext(System.out::println)
                  .then())
            .block(); // to subscribe and return the value
   }
}