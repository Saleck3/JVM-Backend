package com.jvm.lecti.service;

import java.util.ArrayList;

import com.jvm.lecti.entity.ModulelEntity;

public interface ModuleService {

   ArrayList<ModulelEntity> getModulesByUserId(Integer id);

}



