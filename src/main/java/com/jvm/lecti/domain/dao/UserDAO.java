package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    List<User> findAllByEmail(String email);

    Optional<User> findByEmail(String email);

    void save(User user);
}
