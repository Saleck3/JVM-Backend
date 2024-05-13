package com.jvm.lecti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   List<User> findAllByEmail(String email);

   Optional<User> findByEmail(String email);


}
