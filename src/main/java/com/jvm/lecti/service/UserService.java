package com.jvm.lecti.service;

import com.jvm.lecti.entity.User;
import com.jvm.lecti.exceptions.userNotFoundException;

public interface UserService {

   User getUserByEmail(String mail) throws userNotFoundException;

}
