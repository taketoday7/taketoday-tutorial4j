package cn.tuyucheng.taketoday.implementsvsextends.player.impl;

import cn.tuyucheng.taketoday.implementsvsextends.media.player.impl.VideoMediaPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class VideoMediaPlayerUnitTest {

   private final PrintStream standardOut = System.out;
   private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

   @BeforeEach
   public void setUp() {
      System.setOut(new PrintStream(outputStreamCaptor));
   }

   @AfterEach
   public void tearDown() {
      System.setOut(standardOut);
   }

   @Test
   public void givenVideoMediaPlayer_whenPlay_thenPrintsPlayingString() {
      VideoMediaPlayer videoMediaPlayer = new VideoMediaPlayer();
      videoMediaPlayer.play();
      Assertions.assertEquals("VideoMediaPlayer is Playing", outputStreamCaptor.toString()
            .trim());
   }

   @Test
   public void givenVideoMediaPlayer_whenPause_thenPrintsPausedString() {
      VideoMediaPlayer videoMediaPlayer = new VideoMediaPlayer();
      videoMediaPlayer.pause();
      Assertions.assertEquals("VideoMediaPlayer is Paused", outputStreamCaptor.toString()
            .trim());
   }
}