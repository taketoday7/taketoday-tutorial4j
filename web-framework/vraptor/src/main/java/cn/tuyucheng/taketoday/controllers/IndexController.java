package cn.tuyucheng.taketoday.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.freemarker.FreemarkerView;
import cn.tuyucheng.taketoday.daos.PostDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller
public class IndexController {

   private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
   private final Result result;
   private PostDao postDao;

   public IndexController() {
      this(null, null);
   }

   @Inject
   public IndexController(Result result, PostDao postDao) {
      this.result = result;
      this.postDao = postDao;
   }

   @Path("/")
   public void index() {
      result.include("posts", postDao.all());
      result.use(FreemarkerView.class).withTemplate("index");
   }


}