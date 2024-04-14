package cn.tuyucheng.taketoday.propertyeditor;

import cn.tuyucheng.taketoday.propertyeditor.creditcard.CreditCard;
import cn.tuyucheng.taketoday.propertyeditor.exotictype.editor.CustomExoticTypeEditor;
import cn.tuyucheng.taketoday.propertyeditor.exotictype.model.ExoticType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/property-editor")
public class PropertyEditorRestController {

   @GetMapping(value = "/credit-card/{card-no}", produces = MediaType.APPLICATION_JSON_VALUE)
   public CreditCard parseCreditCardNumber(@PathVariable("card-no") CreditCard creditCard) {
      return creditCard;
   }

   @GetMapping(value = "/exotic-type/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ExoticType parseExoticType(@PathVariable("value") ExoticType exoticType) {
      return exoticType;
   }

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(ExoticType.class, new CustomExoticTypeEditor());
   }
}