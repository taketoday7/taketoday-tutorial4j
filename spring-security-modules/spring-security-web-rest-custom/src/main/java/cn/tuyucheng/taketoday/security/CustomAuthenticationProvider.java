package cn.tuyucheng.taketoday.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

   public CustomAuthenticationProvider() {
      super();
   }

   @Override
   public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
      final String name = authentication.getName();
      final String password = authentication.getCredentials().toString();
      if (name.equals("admin") && password.equals("system")) {
         final List<GrantedAuthority> grantedAuths = new ArrayList<>();
         grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
         final UserDetails principal = new User(name, password, grantedAuths);
         final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
         return auth;
      } else {
         return null;
      }
   }

   @Override
   public boolean supports(final Class<?> authentication) {
      return authentication.equals(UsernamePasswordAuthenticationToken.class);
   }
}