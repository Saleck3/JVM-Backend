package com.jvm.lecti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.domain.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
