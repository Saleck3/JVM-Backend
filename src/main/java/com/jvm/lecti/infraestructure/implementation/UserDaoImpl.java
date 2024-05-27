package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.UserDAO;
import com.jvm.lecti.domain.entity.User;
import com.jvm.lecti.infraestructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDAO {

    @Autowired
    private UserRepository userRepository;

    public UserDaoImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
