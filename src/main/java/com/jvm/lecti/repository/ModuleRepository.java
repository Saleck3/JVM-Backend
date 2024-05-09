package com.jvm.lecti.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jvm.lecti.entity.ModulelEntity;

@Repository
public interface ModuleRepository extends JpaRepository<ModulelEntity, Integer> {

  ArrayList<ModulelEntity> findAllById(Integer id);
}
