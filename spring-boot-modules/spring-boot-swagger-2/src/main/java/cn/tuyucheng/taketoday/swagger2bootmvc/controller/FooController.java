package cn.tuyucheng.taketoday.swagger2bootmvc.controller;

import cn.tuyucheng.taketoday.swagger2bootmvc.model.Foo;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Controller
public class FooController {

   public FooController() {
      super();
   }

   // API - write
   @RequestMapping(method = RequestMethod.POST, value = "/foos")
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   @Parameters({@Parameter(name = "foo", description = "List of strings")})
   public Foo create(@RequestBody final Foo foo) {
      foo.setId(Long.parseLong(randomNumeric(2)));
      return foo;
   }
}