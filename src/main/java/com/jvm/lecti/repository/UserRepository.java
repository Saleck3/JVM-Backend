package com.jvm.lecti.repository;

import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.User;

@Repository
public class UserRepository {

   public User findUserByEmail(String email) {
      String password = new MessageDigestPasswordEncoder("SHA-256").encode("123456");
      User user = new User(email, password);
      user.setFirstName(password);
      user.setLastName("Fernandez");
      return user;
   }

}
