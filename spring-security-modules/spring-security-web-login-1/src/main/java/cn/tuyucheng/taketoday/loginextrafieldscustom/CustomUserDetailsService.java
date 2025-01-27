package cn.tuyucheng.taketoday.loginextrafieldscustom;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomUserDetailsService {

   UserDetails loadUserByUsernameAndDomain(String username, String domain) throws UsernameNotFoundException;

}
