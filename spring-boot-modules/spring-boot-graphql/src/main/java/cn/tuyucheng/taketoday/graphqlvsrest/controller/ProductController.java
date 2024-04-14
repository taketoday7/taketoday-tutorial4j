package cn.tuyucheng.taketoday.graphqlvsrest.controller;

import cn.tuyucheng.taketoday.graphqlvsrest.entity.Product;
import cn.tuyucheng.taketoday.graphqlvsrest.model.ProductModel;
import cn.tuyucheng.taketoday.graphqlvsrest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

   @Autowired
   ProductRepository productRepository;

   @GetMapping
   public List<Product> getProducts(Pageable pageable) {
      return productRepository.getProducts(pageable.getPageSize(), pageable.getPageNumber());
   }

   @GetMapping("/{product-id}")
   public Product getProduct(@PathVariable("product-id") Integer productId) {
      return productRepository.getProduct(productId);
   }

   @PostMapping
   public Product save(@RequestBody ProductModel productModel) {
      return productRepository.save(productModel);
   }

   @PutMapping("/{product-id}")
   public Product update(@PathVariable("product-id") Integer productId, @RequestBody ProductModel productModel) {
      return productRepository.update(productId, productModel);
   }
}