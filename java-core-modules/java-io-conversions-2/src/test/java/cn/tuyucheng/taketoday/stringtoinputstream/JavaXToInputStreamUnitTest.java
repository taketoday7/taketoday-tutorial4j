package cn.tuyucheng.taketoday.stringtoinputstream;

import com.google.common.io.CharSource;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JavaXToInputStreamUnitTest {
   protected final Logger logger = LoggerFactory.getLogger(getClass());

   // tests - String - InputStream

   @Test
   public final void givenUsingPlainJava_whenConvertingStringToInputStream_thenCorrect() throws IOException {
      final String initialString = "text";
      final InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());

      IOUtils.closeQuietly(targetStream);
   }

   @Test
   public final void givenUsingGuava_whenConvertingStringToInputStream_thenCorrect() throws IOException {
      final String initialString = "text";
      final InputStream targetStream = CharSource.wrap(initialString).asByteSource(StandardCharsets.UTF_8).openStream();

      IOUtils.closeQuietly(targetStream);
   }

   @Test
   public final void givenUsingCommonsIO_whenConvertingStringToInputStream_thenCorrect() throws IOException {
      final String initialString = "text";
      final InputStream targetStream = IOUtils.toInputStream(initialString);

      IOUtils.closeQuietly(targetStream);
   }

}
