package cn.tuyucheng.taketoday.spring.cloud.aws.sqs.acknowledgement.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.UUID;

@ConfigurationProperties("product.id")
public class ProductIdProperties {

   private UUID smartphone;

   private UUID wirelessHeadphones;

   private UUID laptop;

   public UUID getSmartphone() {
      return smartphone;
   }

   public void setSmartphone(UUID smartphone) {
      this.smartphone = smartphone;
   }

   public UUID getWirelessHeadphones() {
      return wirelessHeadphones;
   }

   public void setWirelessHeadphones(UUID wirelessHeadphones) {
      this.wirelessHeadphones = wirelessHeadphones;
   }

   public UUID getLaptop() {
      return laptop;
   }

   public void setLaptop(UUID laptop) {
      this.laptop = laptop;
   }
}