package cn.tuyucheng.taketoday.springsecuredsockets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import cn.tuyucheng.taketoday.springsecuredsockets.security.CustomAccessDeniedHandler;
import cn.tuyucheng.taketoday.springsecuredsockets.security.CustomDaoAuthenticationProvider;
import cn.tuyucheng.taketoday.springsecuredsockets.security.CustomLoginSuccessHandler;
import cn.tuyucheng.taketoday.springsecuredsockets.security.CustomLogoutSuccessHandler;
import cn.tuyucheng.taketoday.springsecuredsockets.security.CustomUserDetailsService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
@ComponentScan("cn.tuyucheng.taketoday.springsecuredsockets")
public class SecurityConfig {

   @Autowired
   private CustomUserDetailsService customUserDetailsService;


   @Bean
   public AccessDeniedHandler accessDeniedHandler() {
      return new CustomAccessDeniedHandler();
   }

   @Bean
   public LogoutSuccessHandler logoutSuccessHandler() {
      return new CustomLogoutSuccessHandler();
   }

   @Bean
   public AuthenticationSuccessHandler loginSuccessHandler() {
      return new CustomLoginSuccessHandler();
   }


   @Bean
   public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
      final DaoAuthenticationProvider bean = new CustomDaoAuthenticationProvider();
      bean.setUserDetailsService(customUserDetailsService);
      bean.setPasswordEncoder(encoder());
      return bean;
   }

   /**
    * Order of precedence is very important.
    * <p>
    * Matching occurs from top to bottom - so, the topmost match succeeds first.
    */
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                  authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/", "/index", "/authenticate").permitAll()
                        .requestMatchers("/secured/**/**", "/secured/**/**/**", "/secured/socket", "/secured/success").authenticated()
                        .anyRequest().authenticated())
            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/login").permitAll()
                  .usernameParameter("username")
                  .passwordParameter("password")
                  .loginProcessingUrl("/authenticate")
                  .successHandler(loginSuccessHandler())
                  .failureUrl("/denied").permitAll())
            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.logoutSuccessHandler(logoutSuccessHandler()))
            /**
             * Applies to User Roles - not to login failures or unauthenticated access attempts.
             */
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(accessDeniedHandler()))
            .authenticationProvider(authenticationProvider());

      /** Disabled for local testing */
      http.csrf(AbstractHttpConfigurer::disable);

      /** This is solely required to support H2 console viewing in Spring MVC with Spring Security */
      http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
            .authorizeHttpRequests(Customizer.withDefaults());
      return http.build();
   }

   @Bean
   public AuthenticationManager authManager(HttpSecurity http) throws Exception {
      return http.getSharedObject(AuthenticationManagerBuilder.class)
            .authenticationProvider(authenticationProvider())
            .build();
   }

   @Bean
   public WebSecurityCustomizer webSecurityCustomizer() {
      return (web) -> web.ignoring().requestMatchers("/resources/**");
   }
}