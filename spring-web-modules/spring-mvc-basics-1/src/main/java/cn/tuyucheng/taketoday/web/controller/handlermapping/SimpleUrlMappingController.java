package cn.tuyucheng.taketoday.web.controller.handlermapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SimpleUrlMappingController extends AbstractController {
   @Override
   protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ModelAndView model = new ModelAndView("simple-url-handler-mapping");
      return model;
   }
}