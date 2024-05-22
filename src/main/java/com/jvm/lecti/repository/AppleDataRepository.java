package com.jvm.lecti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Apple;

@Repository
public interface AppleDataRepository  extends JpaRepository<Apple, Integer> {

   @Query("SELECT a FROM Apple a where a.id = :id")
   List<Apple> findAllById(Integer id);

   @Query("SELECT a FROM Apple a JOIN a.modulo m WHERE m.id = :moduleId")
   List<Apple> findAllByModuleId(int moduleId);

}
