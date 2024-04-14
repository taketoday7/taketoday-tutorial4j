package cn.tuyucheng.taketoday.webservice.generated;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 *
 * <p>以下模式片段指定包含在此类中的预期内容。
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="product" type="{http://tuyucheng.com/spring-boot-web-service}product"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
      "product"
})
@XmlRootElement(name = "getProductResponse")
public class GetProductResponse {

   @XmlElement(required = true)
   protected Product product;

   /**
    * 获取product属性的值。
    *
    * @return possible object is
    * {@link Product }
    */
   public Product getProduct() {
      return product;
   }

   /**
    * 设置product属性的值。
    *
    * @param value allowed object is
    *              {@link Product }
    */
   public void setProduct(Product value) {
      this.product = value;
   }

}
