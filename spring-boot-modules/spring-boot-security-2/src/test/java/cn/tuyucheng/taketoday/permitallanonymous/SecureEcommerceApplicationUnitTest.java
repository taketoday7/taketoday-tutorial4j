package cn.tuyucheng.taketoday.permitallanonymous;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SecuredEcommerceApplication.class)
@AutoConfigureMockMvc
class SecureEcommerceApplicationUnitTest {
   @Autowired
   private MockMvc mockMvc;
   private static final Logger logger = LoggerFactory.getLogger(SecureEcommerceApplicationUnitTest.class);

   @WithAnonymousUser
   @Test
   void givenAnonymousUser_whenAccessToUserRegisterPage_thenAllowAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/public/registerUser"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Register User"));
   }

   @WithMockUser(username = "spring", password = "secret")
   @Test
   void givenAuthenticatedUser_whenAccessToUserRegisterPage_thenDenyAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/public/registerUser"))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
   }

   @WithMockUser(username = "spring", password = "secret")
   @Test
   void givenAuthenticatedUser_whenAccessToProductLinePage_thenAllowAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/public/showProducts"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("List Products"));
   }

   @WithAnonymousUser
   @Test
   void givenAnonymousUser_whenAccessToProductLinePage_thenAllowAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/public/showProducts"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("List Products"));
   }

   @WithMockUser(username = "spring", password = "secret")
   @Test
   void givenAuthenticatedUser_whenAccessToCartPage_thenAllowAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/private/showCart"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("Show Cart"));
   }

   @WithAnonymousUser
   @Test
   void givenAnonymousUser_whenAccessToCartPage_thenDenyAccess() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/private/showCart"))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
   }
}