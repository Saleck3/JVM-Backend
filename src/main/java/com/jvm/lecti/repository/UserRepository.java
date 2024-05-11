package com.jvm.lecti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
