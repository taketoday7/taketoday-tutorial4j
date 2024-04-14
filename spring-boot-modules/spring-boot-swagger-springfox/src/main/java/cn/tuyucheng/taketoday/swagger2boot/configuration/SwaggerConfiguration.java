package cn.tuyucheng.taketoday.swagger2boot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;

import java.util.Collections;

@Configuration
public class SwaggerConfiguration {

   private ApiInfo apiInfo() {
      return new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service",
            new Contact("Umang Budhwar", "www.tuyucheng.com", "umangbudhwar@gmail.com"),
            "License of API", "API license URL", Collections.emptyList());
   }

   @Bean
   public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
   }

   /**
    * SwaggerUI information
    */
   @Bean
   UiConfiguration uiConfig() {
      return UiConfigurationBuilder.builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(false)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
            .validatorUrl(null)
            .build();
   }
}