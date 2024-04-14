package cn.tuyucheng.taketoday.springbootredis.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Builder
@EqualsAndHashCode
@RedisHash(timeToLive = 60L)
@Data
@NoArgsConstructor
public class Session {
   @Id
   private String id;
   @TimeToLive
   private Long expirationInSeconds;

   public Session(String id, Long expirationInSeconds) {
      this.id = id;
      this.expirationInSeconds = expirationInSeconds;
   }
}