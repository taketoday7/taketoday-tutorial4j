package cn.tuyucheng.taketoday.spring.cloud.openservicebroker.services;

import cn.tuyucheng.taketoday.spring.cloud.openservicebroker.mail.MailService;
import cn.tuyucheng.taketoday.spring.cloud.openservicebroker.mail.MailServiceBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.cloud.servicebroker.exception.ServiceInstanceBindingDoesNotExistException;
import org.springframework.cloud.servicebroker.model.binding.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.HashMap;
import java.util.Map;

import static cn.tuyucheng.taketoday.spring.cloud.openservicebroker.mail.MailService.*;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MailServiceInstanceBindingServiceUnitTest {

   private static final String MAIL_SERVICE_INSTANCE_ID = "test@baeldung.com";
   private static final String MAIL_SERVICE_BINDING_ID = "test";
   private static final String MAIL_SYSTEM_URL = "http://localhost:8080/mail-system/test@baeldung.com";

   @Mock
   private MailService mailService;

   private MailServiceInstanceBindingService mailServiceInstanceBindingService;

   @BeforeEach
   void setUp() {
      openMocks(this);

      this.mailServiceInstanceBindingService = new MailServiceInstanceBindingService(mailService);
   }

   @Test
   void givenServiceBindingDoesNotExist_whenCreateServiceBinding_thenNewBindingIsCreated() {
      // given service binding does not exist
      when(mailService.serviceBindingExists(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID)).thenReturn(Mono.just(false));

      Map<String, Object> credentials = generateCredentials();
      MailServiceBinding serviceBinding = new MailServiceBinding(MAIL_SERVICE_BINDING_ID, credentials);
      when(mailService.createServiceBinding(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID))
            .thenReturn(Mono.just(serviceBinding));

      // when create service binding
      CreateServiceInstanceBindingRequest request = CreateServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then a new service binding is provisioned
      StepVerifier.create(mailServiceInstanceBindingService.createServiceInstanceBinding(request))
            .consumeNextWith(response -> {
               assertInstanceOf(CreateServiceInstanceAppBindingResponse.class, response);
               CreateServiceInstanceAppBindingResponse bindingResponse = (CreateServiceInstanceAppBindingResponse) response;
               assertFalse(bindingResponse.isBindingExisted());
               validateBindingCredentials(bindingResponse.getCredentials());
            })
            .verifyComplete();
   }

   @Test
   void givenServiceBindingExists_whenCreateServiceBinding_thenExistingBindingIsRetrieved() {
      // given service binding exists
      when(mailService.serviceBindingExists(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID)).thenReturn(Mono.just(true));

      Map<String, Object> credentials = generateCredentials();
      MailServiceBinding serviceBinding = new MailServiceBinding(MAIL_SERVICE_BINDING_ID, credentials);
      when(mailService.getServiceBinding(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID))
            .thenReturn(Mono.just(serviceBinding));

      // when create service binding
      CreateServiceInstanceBindingRequest request = CreateServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then a new service binding is provisioned
      StepVerifier.create(mailServiceInstanceBindingService.createServiceInstanceBinding(request))
            .consumeNextWith(response -> {
               assertInstanceOf(CreateServiceInstanceAppBindingResponse.class, response);
               CreateServiceInstanceAppBindingResponse bindingResponse = (CreateServiceInstanceAppBindingResponse) response;
               assertTrue(bindingResponse.isBindingExisted());
               validateBindingCredentials(bindingResponse.getCredentials());
            })
            .verifyComplete();
   }

   @Test
   void givenServiceBindingDoesNotExist_whenGetServiceBinding_thenException() {
      // given service binding does not exist
      when(mailService.getServiceBinding(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID)).thenReturn(Mono.empty());

      // when get service binding
      GetServiceInstanceBindingRequest request = GetServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then ServiceInstanceBindingDoesNotExistException is thrown
      StepVerifier.create(mailServiceInstanceBindingService.getServiceInstanceBinding(request))
            .expectErrorMatches(ServiceInstanceBindingDoesNotExistException.class::isInstance)
            .verify();
   }

   @Test
   void givenServiceBindingExists_whenGetServiceBinding_thenExistingBindingIsRetrieved() {
      // given service binding exists
      Map<String, Object> credentials = generateCredentials();
      MailServiceBinding serviceBinding = new MailServiceBinding(MAIL_SERVICE_BINDING_ID, credentials);
      when(mailService.getServiceBinding(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID))
            .thenReturn(Mono.just(serviceBinding));

      // when get service binding
      GetServiceInstanceBindingRequest request = GetServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then the existing service binding is retrieved
      StepVerifier.create(mailServiceInstanceBindingService.getServiceInstanceBinding(request))
            .consumeNextWith(response -> {
               assertInstanceOf(GetServiceInstanceAppBindingResponse.class, response);
               GetServiceInstanceAppBindingResponse bindingResponse = (GetServiceInstanceAppBindingResponse) response;
               validateBindingCredentials(bindingResponse.getCredentials());
            })
            .verifyComplete();
   }

   @Test
   void givenServiceBindingDoesNotExist_whenDeleteServiceBinding_thenException() {
      // given service binding does not exist
      when(mailService.serviceBindingExists(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID)).thenReturn(Mono.just(false));

      // when delete service binding
      DeleteServiceInstanceBindingRequest request = DeleteServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then ServiceInstanceBindingDoesNotExistException is thrown
      StepVerifier.create(mailServiceInstanceBindingService.deleteServiceInstanceBinding(request))
            .expectErrorMatches(ServiceInstanceBindingDoesNotExistException.class::isInstance)
            .verify();
   }

   @Test
   void givenServiceBindingExists_whenDeleteServiceBinding_thenExistingBindingIsDeleted() {
      // given service binding exists
      when(mailService.serviceBindingExists(MAIL_SERVICE_INSTANCE_ID, MAIL_SERVICE_BINDING_ID)).thenReturn(Mono.just(true));
      when(mailService.deleteServiceBinding(MAIL_SERVICE_INSTANCE_ID)).thenReturn(Mono.empty());

      // when delete service binding
      DeleteServiceInstanceBindingRequest request = DeleteServiceInstanceBindingRequest.builder()
            .serviceInstanceId(MAIL_SERVICE_INSTANCE_ID)
            .bindingId(MAIL_SERVICE_BINDING_ID)
            .build();

      // then the existing service binding is retrieved
      StepVerifier.create(mailServiceInstanceBindingService.deleteServiceInstanceBinding(request))
            .consumeNextWith(response -> {
               assertFalse(response.isAsync());
               assertNull(response.getOperation());
            })
            .verifyComplete();
   }

   private void validateBindingCredentials(Map<String, Object> bindingCredentials) {
      assertNotNull(bindingCredentials);
      assertEquals(3, bindingCredentials.size());
      assertTrue(bindingCredentials.containsKey(URI_KEY));
      assertTrue(bindingCredentials.containsKey(USERNAME_KEY));
      assertTrue(bindingCredentials.containsKey(PASSWORD_KEY));
      assertEquals(MAIL_SYSTEM_URL, bindingCredentials.get(URI_KEY));
      assertEquals(MAIL_SERVICE_BINDING_ID, bindingCredentials.get(USERNAME_KEY));
      assertNotNull(bindingCredentials.get(PASSWORD_KEY));
   }

   private Map<String, Object> generateCredentials() {
      Map<String, Object> credentials = new HashMap<>();
      credentials.put(URI_KEY, MAIL_SYSTEM_URL);
      credentials.put(USERNAME_KEY, MAIL_SERVICE_BINDING_ID);
      credentials.put(PASSWORD_KEY, randomUUID().toString());
      return credentials;
   }
}