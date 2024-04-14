package cn.tuyucheng.taketoday.multiplecachemanager.repository;

import cn.tuyucheng.taketoday.multiplecachemanager.entity.Item;
import cn.tuyucheng.taketoday.multiplecachemanager.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailRepository {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   public Order getOrderDetail(Integer orderId) {
      String orderDetailQuery = "select * from orderdetail where orderid = ? ";
      Order order = new Order();
      jdbcTemplate.query(orderDetailQuery, new Object[]{orderId}, rs -> {
         order.setCustomerId(rs.getInt("customerid"));
         order.setOrderId(rs.getInt("orderid"));
         order.setItemId(rs.getInt("itemid"));
         order.setQuantity(rs.getInt("quantity"));
      });
      return order;
   }

   public double getOrderPrice(Integer orderId) {
      String orderItemJoinQuery = "select * from ( select * from orderdetail where orderid = ? ) o left join item on o.itemid = ITEM.itemid";
      Order order = new Order();
      Item item = new Item();

      jdbcTemplate.query(orderItemJoinQuery, new Object[]{orderId}, rs -> {
         order.setCustomerId(rs.getInt("customerid"));
         order.setOrderId(rs.getInt("orderid"));
         order.setItemId(rs.getInt("itemid"));
         order.setQuantity(rs.getInt("quantity"));
         item.setItemDesc("itemdesc");
         item.setItemId(rs.getInt("itemid"));
         item.setItemPrice(rs.getDouble("price"));
      });
      return order.getQuantity() * item.getItemPrice();
   }
}