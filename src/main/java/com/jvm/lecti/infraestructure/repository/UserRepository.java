package com.jvm.lecti.infraestructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   List<User> findAllByEmail(String email);

   Optional<User> findByEmail(String email);


}
