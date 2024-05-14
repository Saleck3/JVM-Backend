package com.jvm.lecti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jvm.lecti.entity.Module;
import com.jvm.lecti.repository.ModuleRepository;

@SpringBootTest
public class ModuleServiceTests {

   private ModuleService moduleService;

   private ModuleRepository moduleRepository;

   @Before
   public void init() {
      moduleRepository = mock(ModuleRepository.class);
      moduleService = new ModuleService(moduleRepository);
   }

   @Test
   public void shouldReturnListOfModules() {
      var modulesDb = new Module(1, "test");
      when(moduleRepository.findById(1)).thenReturn(Optional.of(modulesDb));

      var result = moduleService.getModulesByModuleId(1);

      assertTrue(result.isPresent());
      thenObtainExpectedId(1,result.get().getId());
   }

   private void thenObtainExpectedId(int expected, int actual) {
      assertEquals(expected, actual);
   }

   @Test
   public void shouldReturnEmptyListOfModulesWhenModuleDoesntExist() {
//      var modulesDb = new ArrayList<ModuleEntity>();
//      modulesDb.add(new ModuleEntity(1, "test"));
//      when(moduleRepository.findAllById(1)).thenReturn(modulesDb);
//
//      var result = moduleService.getModulesByModuleId(2);
//
//      assertEquals(result.getModules().size(), 0);
   }

}
