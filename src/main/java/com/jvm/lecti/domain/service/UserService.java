package com.jvm.lecti.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.exceptions.userNotFoundException;
import com.jvm.lecti.infraestructure.repository.UserRepository;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   public User getUserByEmail(String mail) throws userNotFoundException {
      Optional<User> user = userRepository.findByEmail(mail);
      if (user.isEmpty()) {
         throw new userNotFoundException();
      }
      return user.get();
   }

}

