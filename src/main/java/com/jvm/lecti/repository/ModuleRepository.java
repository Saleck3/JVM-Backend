package com.jvm.lecti.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
