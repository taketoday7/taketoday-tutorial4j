package cn.tuyucheng.taketoday;

import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class SummationServiceIntegrationTest extends TestNG {
   private List<Integer> numbers;

   private int testCount = 0;

   @BeforeClass
   public void initialize() {
      numbers = new ArrayList<>();
   }

   @AfterClass
   public void tearDown() {
      numbers = null;
   }

   @BeforeSuite(groups = "regression")
   public void runBeforeRegressionSuite() {
      numbers = new ArrayList<>();
      numbers.add(-11);
      numbers.add(2);
   }

   @AfterSuite(groups = "regression")
   public void runAfterRegressionSuite() {
      numbers = null;
   }

   @BeforeGroups("negative_tests")
   public void runBeforeEachNegativeGroup() {
      numbers.clear();
   }

   @BeforeGroups("regression")
   public void runBeforeEachRegressionGroup() {
      numbers.add(-11);
      numbers.add(2);
   }

   @BeforeGroups("positive_tests")
   public void runBeforeEachPositiveGroup() {
      numbers.add(1);
      numbers.add(2);
      numbers.add(3);
   }

   @AfterGroups("positive_tests,regression,negative_tests")
   public void runAfterEachGroup() {
      numbers.clear();
   }

   @BeforeMethod
   public void runBeforeEachTest() {
      testCount++;
   }

   @AfterMethod
   public void runAfterEachTest() {

   }

   @Test(groups = "positive_tests", enabled = false)
   public void givenNumbers_sumEquals_thenCorrect() {
      int sum = numbers.stream().reduce(0, Integer::sum);
      Assert.assertEquals(sum, 6);
   }

   @Test(groups = "negative_tests")
   @Ignore
   public void givenEmptyList_sumEqualsZero_thenCorrect() {
      int sum = numbers.stream().reduce(0, Integer::sum);
      Assert.assertEquals(sum, 0);
   }

   @Test(groups = "regression")
   @Ignore
   public void givenNegativeNumber_sumLessthanZero_thenCorrect() {
      int sum = numbers.stream().reduce(0, Integer::sum);
      Assert.assertTrue(sum < 0);
   }

   @Test(expectedExceptions = ArithmeticException.class)
   public void givenNumber_whenThrowsException_thenCorrect() {
      int i = 1 / 0;
   }
}