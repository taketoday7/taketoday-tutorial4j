package cn.tuyucheng.taketoday.javers.service;

import cn.tuyucheng.taketoday.javers.domain.Product;
import cn.tuyucheng.taketoday.javers.domain.Store;
import cn.tuyucheng.taketoday.javers.repo.ProductRepository;
import cn.tuyucheng.taketoday.javers.repo.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class StoreService {
   private final ProductRepository productRepository;
   private final StoreRepository storeRepository;

   public StoreService(ProductRepository productRepository, StoreRepository storeRepository) {
      this.productRepository = productRepository;
      this.storeRepository = storeRepository;
   }

   public void updateProductPrice(Integer productId, Double price) {
      Optional<Product> productOpt = productRepository.findById(productId);
      productOpt.ifPresent(product -> {
         product.setPrice(price);
         productRepository.save(product);
      });
   }

   public void rebrandStore(int storeId, String updatedName) {
      Optional<Store> storeOpt = storeRepository.findById(storeId);
      storeOpt.ifPresent(store -> {
         store.setName(updatedName);
         store.getProducts().forEach(product -> product.setNamePrefix(updatedName));
         storeRepository.save(store);
      });
   }

   public void createRandomProduct(Integer storeId) {
      Optional<Store> storeOpt = this.storeRepository.findById(storeId);
      storeOpt.ifPresent(store -> {
         Random random = new Random();
         Product product = new Product(STR."Product#\{random.nextInt()}", random.nextDouble() * 100);
         store.addProduct(product);
         storeRepository.save(store);
      });
   }

   public Store findStoreById(int storeId) {
      return storeRepository.findById(storeId).get();
   }

   public Product findProductById(int id) {
      return this.productRepository.findById(id).get();
   }
}