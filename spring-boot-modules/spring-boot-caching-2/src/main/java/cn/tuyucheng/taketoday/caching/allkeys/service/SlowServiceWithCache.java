package cn.tuyucheng.taketoday.caching.allkeys.service;

import org.springframework.cache.annotation.CachePut;

public class SlowServiceWithCache {

   @CachePut(cacheNames = "slowServiceCache", key = "#name")
   public String save(String name, String details) {
      return details;
   }
}