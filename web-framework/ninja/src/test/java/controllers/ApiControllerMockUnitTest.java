package controllers;

import ninja.NinjaTest;
import ninja.Result;
import org.junit.Before;
import org.junit.Test;
import services.UserService;

import static org.junit.Assert.assertEquals;

public class ApiControllerMockUnitTest extends NinjaTest {

   private UserService userService;

   private ApplicationController applicationController;

   @Before
   public void setupTest() {
      userService = this.ninjaTestServer.getInjector().getInstance(UserService.class);
      applicationController = new ApplicationController();
      applicationController.userService = userService;
   }

   @Test
   public void testThatGetUserJson() {
      Result result = applicationController.userJson();
      System.out.println(result.getRenderable());
      assertEquals(userService.getUserMap().toString(), result.getRenderable().toString());
   }
}