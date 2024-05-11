package com.jvm.lecti.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jvm.lecti.entity.ModuleEntity;
import com.jvm.lecti.repository.ModuleRepository;

@SpringBootTest
public class ModuleServiceTests {

   private ModuleService moduleService;

   private ModuleRepository moduleRepository;

   @Before
   public void init() {
      moduleRepository = mock(ModuleRepository.class);
      moduleService = new ModuleServiceImpl(moduleRepository);
   }

   @Test
   public void shouldReturnListOfModules() {
      var modulesDb = new ArrayList<ModuleEntity>();
      modulesDb.add(new ModuleEntity(1, "test"));
      when(moduleRepository.findAllById(1)).thenReturn(modulesDb);

      var result = moduleService.getModulesByModuleId(1);

      assertEquals(result.getModules().size(), 1);
   }

   @Test
   public void shouldReturnEmptyListOfModulesWhenModuleDoestExist() {
      var modulesDb = new ArrayList<ModuleEntity>();
      modulesDb.add(new ModuleEntity(1, "test"));
      when(moduleRepository.findAllById(1)).thenReturn(modulesDb);

      var result = moduleService.getModulesByModuleId(2);

      assertEquals(result.getModules().size(), 0);
   }

}
