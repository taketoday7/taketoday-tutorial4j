package cn.tuyucheng.taketoday.mockito.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FlowerJsonStringValidator {
   private final ObjectMapper objectMapper;

   public FlowerJsonStringValidator(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
   }

   public boolean flowerHasPetals(String jsonFlowerAsString) throws JsonProcessingException {
      Flower flower = objectMapper.readValue(jsonFlowerAsString, Flower.class);
      return flower.getPetals() > 0;
   }
}