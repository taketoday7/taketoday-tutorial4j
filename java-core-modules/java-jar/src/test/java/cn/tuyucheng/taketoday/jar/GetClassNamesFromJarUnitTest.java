package cn.tuyucheng.taketoday.jar;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class GetClassNamesFromJarUnitTest {
   private static final String JAR_PATH = "example-jar/stripe-0.0.1-SNAPSHOT.jar";
   private static final Set<String> EXPECTED_CLASS_NAMES = Sets.newHashSet(
         "cn.tuyucheng.taketoday.stripe.StripeApplication",
         "cn.tuyucheng.taketoday.stripe.ChargeRequest",
         "cn.tuyucheng.taketoday.stripe.StripeService",
         "cn.tuyucheng.taketoday.stripe.ChargeRequest$Currency",
         "cn.tuyucheng.taketoday.stripe.ChargeController",
         "cn.tuyucheng.taketoday.stripe.CheckoutController");

   @Test
   public void givenJarFilePath_whenLoadClassNames_thenGetClassNames() throws IOException, URISyntaxException {
      File jarFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(JAR_PATH)).toURI());
      Set<String> classNames = GetClassNamesFromJar.getClassNamesFromJarFile(jarFile);
      Assertions.assertEquals(EXPECTED_CLASS_NAMES, classNames);
   }

   @Test
   public void givenJarFilePath_whenLoadClass_thenGetClassObjects() throws IOException, ClassNotFoundException, URISyntaxException {
      File jarFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(JAR_PATH)).toURI());
      Set<Class> classes = GetClassNamesFromJar.getClassesFromJarFile(jarFile);
      Set<String> names = classes.stream().map(Class::getName).collect(Collectors.toSet());
      Assertions.assertEquals(EXPECTED_CLASS_NAMES, names);
   }
}
