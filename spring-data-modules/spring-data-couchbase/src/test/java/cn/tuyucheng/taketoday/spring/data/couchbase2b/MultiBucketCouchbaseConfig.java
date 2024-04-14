package cn.tuyucheng.taketoday.spring.data.couchbase2b;

import cn.tuyucheng.taketoday.spring.data.couchbase.model.Campus;
import com.couchbase.client.core.env.PasswordAuthenticator;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.env.ClusterEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.core.mapping.event.ValidatingCouchbaseEventListener;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"cn.tuyucheng.taketoday.spring.data.couchbase2b"})
public class MultiBucketCouchbaseConfig extends AbstractCouchbaseConfiguration {

   public static final String NODE_LIST = "localhost";
   public static final String DEFAULT_BUCKET_NAME = "tuyucheng";
   public static final String DEFAULT_BUCKET_USERNAME = "tuyucheng";
   public static final String DEFAULT_BUCKET_PASSWORD = "tuyucheng";

   @Autowired
   private MappingCouchbaseConverter mappingCouchbaseConverter;

   @Bean
   public Bucket campusBucket() {
      return couchbaseCluster(ClusterEnvironment.create()).bucket("tuyucheng2");
   }

   @Bean(name = "campusTemplate")
   public CouchbaseTemplate campusTemplate() {
      return new CouchbaseTemplate(new SimpleCouchbaseClientFactory(NODE_LIST,
            PasswordAuthenticator.create(DEFAULT_BUCKET_USERNAME, DEFAULT_BUCKET_PASSWORD), DEFAULT_BUCKET_NAME), mappingCouchbaseConverter);
   }

   @Override
   public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
      try {
         baseMapping.mapEntity(Campus.class, campusTemplate());
      } catch (Exception e) {
         // custom Exception handling
      }
   }

   @Bean
   public LocalValidatorFactoryBean localValidatorFactoryBean() {
      return new LocalValidatorFactoryBean();
   }

   @Bean
   public ValidatingCouchbaseEventListener validatingCouchbaseEventListener() {
      return new ValidatingCouchbaseEventListener(localValidatorFactoryBean());
   }

   @Override
   public String getConnectionString() {
      return NODE_LIST;
   }

   @Override
   public String getUserName() {
      return DEFAULT_BUCKET_USERNAME;
   }

   @Override
   public String getPassword() {
      return DEFAULT_BUCKET_PASSWORD;
   }

   @Override
   public String getBucketName() {
      return DEFAULT_BUCKET_NAME;
   }
}