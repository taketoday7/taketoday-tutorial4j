package cn.tuyucheng.taketoday.asciiart;

import cn.tuyucheng.taketoday.asciiart.AsciiArt.Settings;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class AsciiArtIntegrationTest {

   @Test
   public void givenTextWithAsciiCharacterAndSettings_shouldPrintAsciiArt() {
      AsciiArt asciiArt = new AsciiArt();
      String text = "BAELDUNG";
      Settings settings = asciiArt.new Settings(new Font("SansSerif", Font.BOLD, 24), text.length() * 30, 30); // 30 pixel width per character

      asciiArt.drawString(text, "*", settings);
   }
}
