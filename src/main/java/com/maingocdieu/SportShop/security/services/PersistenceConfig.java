package com.maingocdieu.SportShop.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement

@EnableJpaAuditing
public class PersistenceConfig {
 
	@Autowired
	AuthenticationManager authenticationManager;
	
	
  @Bean
  AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }
  public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
    	System.out.println(principal);
    	if (principal instanceof UserDetailsImpl) {
    	  String username = ((UserDetailsImpl)principal).getUsername();
    	  return Optional.of(username);
    	} 
     	   return Optional.of("maingocdieu");
    }
  }

}