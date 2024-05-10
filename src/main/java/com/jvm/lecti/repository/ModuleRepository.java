package com.jvm.lecti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jvm.lecti.entity.ModuleEntity;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, Integer> {

   List<ModuleEntity> findAllById(Integer id);

}
