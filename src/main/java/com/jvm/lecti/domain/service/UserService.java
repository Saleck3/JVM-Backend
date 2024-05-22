package com.jvm.lecti.domain.service;

import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.domain.exceptions.UserNotFoundException;

public interface UserService {

   User getUserByEmail(String mail) throws UserNotFoundException;

}
