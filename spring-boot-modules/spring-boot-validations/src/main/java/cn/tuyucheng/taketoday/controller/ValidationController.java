package cn.tuyucheng.taketoday.controller;

import cn.tuyucheng.taketoday.dto.BooleanObject;
import cn.tuyucheng.taketoday.service.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

   @Autowired
   ValidationService service;

   @PostMapping("/validateBoolean")
   public ResponseEntity<String> processBooleanObject(@RequestBody @Valid BooleanObject booleanObj) {
      return ResponseEntity.ok("BooleanObject is valid");
   }

   @PostMapping("/validateBooleanAtService")
   public ResponseEntity<String> processBooleanObjectAtService() {
      BooleanObject boolObj = new BooleanObject();
      boolObj.setBoolField(Boolean.TRUE);
      boolObj.setTrueField(Boolean.FALSE);
      service.processBoolean(boolObj);
      return ResponseEntity.ok("BooleanObject is valid");
   }
}