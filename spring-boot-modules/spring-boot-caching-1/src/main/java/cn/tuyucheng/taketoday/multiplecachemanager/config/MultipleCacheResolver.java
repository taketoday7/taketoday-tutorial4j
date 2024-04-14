package cn.tuyucheng.taketoday.multiplecachemanager.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import java.util.ArrayList;
import java.util.Collection;

public class MultipleCacheResolver implements CacheResolver {

   private final CacheManager simpleCacheManager;

   private final CacheManager caffeineCacheManager;

   private static final String ORDER_CACHE = "orders";

   private static final String ORDER_PRICE_CACHE = "orderprice";

   public MultipleCacheResolver(CacheManager simpleCacheManager, CacheManager caffeineCacheManager) {
      this.simpleCacheManager = simpleCacheManager;
      this.caffeineCacheManager = caffeineCacheManager;

   }

   @Override
   public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
      Collection<Cache> caches = new ArrayList<>();
      if ("getOrderDetail".equals(context.getMethod()
            .getName())) {
         caches.add(caffeineCacheManager.getCache(ORDER_CACHE));
      } else {
         caches.add(simpleCacheManager.getCache(ORDER_PRICE_CACHE));
      }
      return caches;
   }
}