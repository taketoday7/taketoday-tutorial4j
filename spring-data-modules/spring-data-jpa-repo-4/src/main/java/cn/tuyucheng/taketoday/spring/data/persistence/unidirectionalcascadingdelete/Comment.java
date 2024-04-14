package cn.tuyucheng.taketoday.spring.data.persistence.unidirectionalcascadingdelete;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String description;
   private Date date;

   public void setId(long id) {
      this.id = id;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setDate(Date total) {
      this.date = total;
   }

   public long getId() {
      return id;
   }

   public String getDescription() {
      return description;
   }

   public Date getDate() {
      return date;
   }
}