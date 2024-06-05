package com.jvm.lecti.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.SecurityUser;
import com.jvm.lecti.domain.entity.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   private final UserDAO userDAO;

   public CustomUserDetailsService(UserDAO userDAO) {
      this.userDAO = userDAO;
   }

   @Override
   public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Optional<User> userOptional = userDAO.findByEmail(email);
      if (userOptional.isPresent()) {
         return new SecurityUser(userOptional.get());
      }
      return null;
   }

}
