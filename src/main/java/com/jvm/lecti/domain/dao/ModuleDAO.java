package com.jvm.lecti.domain.dao;

import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.entity.Module;

public interface ModuleDAO {

   Optional<Module> findById(Integer id);

   List<Module> findAll();

}
