package com.jvm.lecti.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private final UserRepository userRepository;

   public CustomUserDetailsService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Optional<User> userOptional = userRepository.findByEmail(email);
      if (userOptional.isPresent()) {
         return new SecurityUser(userOptional.get());
      }
      return null;
   }

}
