package com.jvm.lecti.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.entity.User;
import com.jvm.lecti.exceptions.userNotFoundException;
import com.jvm.lecti.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

   @Autowired
   UserRepository userRepository;

   @Override
   public User getUserByEmail(String mail) throws userNotFoundException {
      Optional<User> user = userRepository.findByEmail(mail);
      if(user.isEmpty()){
        throw new userNotFoundException();
      }
      return user.get();
   }

}

