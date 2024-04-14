package cn.tuyucheng.taketoday.mime;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Foo implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   @Column(nullable = false)
   private String name;

   @Version
   private long version;

   public Foo() {
      super();
   }

   public Foo(final String name) {
      super();

      this.name = name;
   }

   public long getId() {
      return id;
   }

   public void setId(final long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(final String name) {
      this.name = name;
   }

   public long getVersion() {
      return version;
   }

   public void setVersion(long version) {
      this.version = version;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((name == null) ? 0 : name.hashCode());
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
      final Foo other = (Foo) obj;
      if (name == null) {
         return other.name == null;
      } else return name.equals(other.name);
   }

   @Override
   public String toString() {
      return STR."Foo [name=\{name}]";
   }
}