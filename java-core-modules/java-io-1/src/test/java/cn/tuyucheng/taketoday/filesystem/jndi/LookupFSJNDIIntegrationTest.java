package cn.tuyucheng.taketoday.filesystem.jndi;

import org.junit.jupiter.api.Test;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;

public class LookupFSJNDIIntegrationTest {
   LookupFSJNDI fsjndi;
   InitialContext ctx = null;
   final String FILENAME = "test.find";

   public LookupFSJNDIIntegrationTest() {
      try {
         fsjndi = new LookupFSJNDI();
      } catch (NamingException e) {
         fsjndi = null;
      }
   }

   @Test
   public void whenInitializationLookupFSJNDIIsNotNull_thenSuccess() {
      assertNotNull("Class LookupFSJNDI has instance", fsjndi);
   }

   @Test
   public void givenLookupFSJNDI_whengetInitialContextIsNotNull_thenSuccess() {
      ctx = fsjndi.getCtx();
      assertNotNull("Context exists", ctx);
   }

   @Test
   public void givenInitialContext_whenLokupFileExists_thenSuccess() {
      File file = fsjndi.getFile(FILENAME);
      assertNotNull("File exists", file);
   }
}
