package com.jvm.lecti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.Apple;

@Repository
public interface AppleRepository extends JpaRepository<Apple, Integer> {
   @Query("SELECT a FROM Apple a JOIN a.modulo m WHERE m.id = :moduleId")
   List<Apple> findAllByModuleId(int moduleId);
}
