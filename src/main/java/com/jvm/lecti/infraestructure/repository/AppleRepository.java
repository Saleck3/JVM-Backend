package com.jvm.lecti.infraestructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Apple;

@Repository
public interface AppleRepository extends JpaRepository<Apple, Integer> {

   @Query("SELECT a FROM Apple a where a.id = :id")
   List<Apple> findAllById(Integer id);

   @Query("SELECT a FROM Apple a JOIN a.module m WHERE m.id = :moduleId AND a.appleType <> 'RECOMMENDED_MODULE' AND a.index IS NOT NULL ORDER BY a.index")
   List<Apple> findAllByModuleIdOrderByIndex(Integer moduleId);

   @Query("SELECT a.id FROM Apple a WHERE a.module.id = :moduleId")
   List<Integer> findAllIdByModuleId(Integer moduleId);

}
