package com.jvm.lecti.domain.service;

import java.util.Optional;

import com.jvm.lecti.domain.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.UserNotFoundException;

@Service
public class UserService {

   @Autowired
   private UserDAO userDAO;

   public User getUserByEmail(String mail) throws UserNotFoundException {
      Optional<User> user = userDAO.findByEmail(mail);
      if (user.isEmpty()) {
         throw new UserNotFoundException();
      }
      return user.get();
   }

}

