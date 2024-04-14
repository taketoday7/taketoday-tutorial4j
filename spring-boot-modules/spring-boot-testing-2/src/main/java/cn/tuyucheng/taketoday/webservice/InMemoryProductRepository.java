package cn.tuyucheng.taketoday.webservice;

import cn.tuyucheng.taketoday.webservice.generated.Product;
import org.springframework.stereotype.Component;

@Component
public class InMemoryProductRepository implements ProductRepository {

   public Product findProduct(String id) {
      Product product = new Product();
      product.setId(id);
      product.setName(STR."Product \{id}");
      return product;
   }
}