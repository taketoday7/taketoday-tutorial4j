package cn.tuyucheng.taketoday.singleton;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarServiceLiveTest {

   public static final Logger LOG = LoggerFactory.getLogger(CarServiceLiveTest.class);
   @Inject
   private CarServiceBean carServiceBean;
   @Inject
   private CarServiceSingleton carServiceSingleton;
   @EJB
   private CarServiceEjbSingleton carServiceEjbSingleton;

   @Deployment
   public static JavaArchive createDeployment() {
      return ShrinkWrap.create(JavaArchive.class)
            .addClasses(CarServiceBean.class, CarServiceSingleton.class, CarServiceEjbSingleton.class, Car.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Test
   public void givenASingleton_whenGetBeanIsCalledTwice_thenTheSameInstanceIsReturned() {
      CarServiceSingleton one = getBean(CarServiceSingleton.class);
      CarServiceSingleton two = getBean(CarServiceSingleton.class);
      assertTrue(one == two);
   }

   @Test
   public void givenAPojo_whenGetBeanIsCalledTwice_thenDifferentInstancesAreReturned() {
      CarServiceBean one = getBean(CarServiceBean.class);
      CarServiceBean two = getBean(CarServiceBean.class);
      assertTrue(one != two);
   }

   @SuppressWarnings("unchecked")
   private <T> T getBean(Class<T> beanClass) {
      BeanManager bm = CDI.current().getBeanManager();
      Bean<T> bean = (Bean<T>) bm.getBeans(beanClass).iterator().next();
      CreationalContext<T> ctx = bm.createCreationalContext(bean);
      return (T) bm.getReference(bean, beanClass, ctx);
   }

   @Test
   public void givenCDI_whenConcurrentAccess_thenLockingIsNotProvided() {
      for (int i = 0; i < 10; i++) {
         new Thread(new Runnable() {
            @Override
            public void run() {
               String model = Double.toString(Math.round(Math.random() * 100));
               Car car = new Car("Speedster", model);
               int serviceQueue = carServiceSingleton.service(car);
               assertTrue(serviceQueue < 10);
            }
         }).start();
      }
      return;
   }

   @Test
   public void givenEJB_whenConcurrentAccess_thenLockingIsProvided() {
      for (int i = 0; i < 10; i++) {
         new Thread(new Runnable() {
            @Override
            public void run() {
               String model = Double.toString(Math.round(Math.random() * 100));
               Car car = new Car("Speedster", model);
               int serviceQueue = carServiceEjbSingleton.service(car);
               assertEquals(0, serviceQueue);
            }
         }).start();
      }
      return;
   }

}