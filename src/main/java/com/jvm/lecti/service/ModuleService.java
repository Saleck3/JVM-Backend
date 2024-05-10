package com.jvm.lecti.service;

import java.util.List;

import com.jvm.lecti.dto.ModuleResponseDto;

public interface ModuleService {

   List<ModuleResponseDto> getModulesByUserId(Integer id);

}



