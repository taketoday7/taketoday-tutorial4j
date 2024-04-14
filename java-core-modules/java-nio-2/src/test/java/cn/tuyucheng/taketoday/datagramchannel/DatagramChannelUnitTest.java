package cn.tuyucheng.taketoday.datagramchannel;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatagramChannelUnitTest {

   @Test
   public void whenClientSendsAndServerReceivesUDPPacket_thenCorrect() throws IOException {
      DatagramChannel server = DatagramServer.startServer();
      DatagramChannel client = DatagramClient.startClient();

      String msg1 = "Hello, this is a Tuyucheng's DatagramChannel based UDP client!";
      String msg2 = "Hi again!, Are you there!";

      InetSocketAddress serverAddress = new InetSocketAddress("localhost", 7001);

      DatagramClient.sendMessage(client, msg1, serverAddress);
      DatagramClient.sendMessage(client, msg2, serverAddress);

      assertEquals("Hello, this is a Tuyucheng's DatagramChannel based UDP client!", DatagramServer.receiveMessage(server));
      assertEquals("Hi again!, Are you there!", DatagramServer.receiveMessage(server));
   }

}
