package cn.tuyucheng.taketoday.restvalidation.service1;

import cn.tuyucheng.taketoday.restvalidation.response.InputFieldError;
import cn.tuyucheng.taketoday.restvalidation.response.UpdateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserService1 {

   @PutMapping(value = "/user1", produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody @Valid User user, BindingResult bindingResult) {
      if (bindingResult.hasFieldErrors()) {
         List<InputFieldError> fieldErrorList = bindingResult.getFieldErrors().stream()
               .map(error -> new InputFieldError(error.getField(), error.getDefaultMessage()))
               .toList();

         UpdateUserResponse updateResponse = new UpdateUserResponse(fieldErrorList);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updateResponse);
      } else {
         // Update logic...
         return ResponseEntity.status(HttpStatus.OK).build();
      }
   }
}