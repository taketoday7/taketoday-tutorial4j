package cn.tuyucheng.taketoday.implementsvsextends.player.impl;

import cn.tuyucheng.taketoday.implementsvsextends.media.player.impl.MultiMediaPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MultiMediaPlayerUnitTest {

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
   public void givenMultiMediaPlayer_whenPlay_thenPrintsPlayingString() {
      MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
      multiMediaPlayer.play();
      Assertions.assertEquals("MultiMediaPlayer is Playing", outputStreamCaptor.toString()
            .trim());
   }

   @Test
   public void givenMultiMediaPlayer_whenPause_thenPrintsPausedString() {
      MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
      multiMediaPlayer.pause();
      Assertions.assertEquals("MultiMediaPlayer is Paused", outputStreamCaptor.toString()
            .trim());
   }

   @Test
   public void givenMultiMediaPlayer_whenSeek_thenPrintsPausedString() {
      MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
      multiMediaPlayer.seek();
      Assertions.assertEquals("MultiMediaPlayer is being seeked", outputStreamCaptor.toString()
            .trim());
   }

   @Test
   public void givenMultiMediaPlayer_whenFastForward_thenPrintsPausedString() {
      MultiMediaPlayer multiMediaPlayer = new MultiMediaPlayer();
      multiMediaPlayer.fastForward();
      Assertions.assertEquals("MultiMediaPlayer is being fast forwarded",
            outputStreamCaptor.toString()
                  .trim());
   }
}