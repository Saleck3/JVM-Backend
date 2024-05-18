package com.jvm.lecti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
   public void ShouldRecover() {
      List<Module> modules = new ArrayList<>();
      when(moduleRepository.findAll()).thenReturn(modules);

   }

}
