package cn.tuyucheng.taketoday.ecommerce.service;

import cn.tuyucheng.taketoday.ecommerce.model.Order;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
public interface OrderService {

   @NotNull Iterable<Order> getAllOrders();

   Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

   void update(@NotNull(message = "The order cannot be null.") @Valid Order order);
}