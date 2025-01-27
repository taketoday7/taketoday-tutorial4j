package cn.tuyucheng.taketoday.web.controller;

import cn.tuyucheng.taketoday.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GetUserWithCustomInterfaceController {

   @Autowired
   private IAuthenticationFacade authenticationFacade;

   public GetUserWithCustomInterfaceController() {
      super();
   }

   @RequestMapping(value = "/username5", method = RequestMethod.GET)
   @ResponseBody
   public String currentUserNameSimple() {
      final Authentication authentication = authenticationFacade.getAuthentication();
      return authentication.getName();
   }

}
