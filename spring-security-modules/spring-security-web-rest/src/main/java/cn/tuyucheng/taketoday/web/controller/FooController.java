package cn.tuyucheng.taketoday.web.controller;

import cn.tuyucheng.taketoday.persistence.model.Foo;
import com.google.common.collect.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Controller
@RequestMapping(value = "/foos")
public class FooController {

   // API

   // read - single
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   @ResponseBody
   public Foo findById(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
      return new Foo(randomAlphabetic(6));
   }

   // read - multiple
   @RequestMapping(method = RequestMethod.GET)
   @ResponseBody
   public List<Foo> findAll() {
      return Lists.newArrayList(new Foo(randomAlphabetic(6)));
   }

   // write - just for test
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(HttpStatus.CREATED)
   @ResponseBody
   public Foo create(@RequestBody final Foo foo) {
      return foo;
   }
}
