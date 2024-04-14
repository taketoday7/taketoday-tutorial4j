package cn.tuyucheng.taketoday.hibernate.fetching.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserEager implements Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue
   @Column(name = "USER_ID")
   private Long userId;

   @OneToMany(fetch = FetchType.EAGER)
   private Set<OrderDetail> orderDetail = new HashSet<>();

   public UserEager() {
   }

   public UserEager(final Long userId) {
      super();
      this.userId = userId;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((userId == null) ? 0 : userId.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      final UserEager other = (UserEager) obj;
      if (userId == null) {
         return other.userId == null;
      } else return userId.equals(other.userId);
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(final Long userId) {
      this.userId = userId;
   }

   public Set<OrderDetail> getOrderDetail() {
      return orderDetail;
   }

   public void setOrderDetail(Set<OrderDetail> orderDetail) {
      this.orderDetail = orderDetail;
   }
}