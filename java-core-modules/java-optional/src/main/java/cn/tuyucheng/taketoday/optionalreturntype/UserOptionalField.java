package cn.tuyucheng.taketoday.optionalreturntype;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Optional;

@Entity
public class UserOptionalField implements Serializable {
   @Id
   private long userId;

   private Optional<String> firstName;

   public long getUserId() {
      return userId;
   }

   public void setUserId(long userId) {
      this.userId = userId;
   }

   public Optional<String> getFirstName() {
      return firstName;
   }

   public void setFirstName(Optional<String> firstName) {
      this.firstName = firstName;
   }
}