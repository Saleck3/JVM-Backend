package com.jvm.lecti.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.entity.Module;
import com.jvm.lecti.repository.AppleRepository;
import com.jvm.lecti.repository.ResultRepository;

@SpringBootTest
public class AppleServiceTest {

   private AppleService appleService;
   private AppleRepository appleRepository;
   private ResultRepository resultRepository;


   @Before
   public void init(){
      appleRepository = mock(AppleRepository.class);
      resultRepository = mock(ResultRepository.class);
      appleService = new AppleServiceImpl(appleRepository, resultRepository);
   }

   @Test
   public void testGetAppleById(){
      Integer id = 1;
      Apple apple = new Apple();
      apple.setId(id);
   }

   @Test
   public void getCorrectAppleDtoWhenGivenExistingAppleId(){
      Integer appleId = 1;
      String description = "MA";
      givenExistingApple(appleId, description);
//      whenAskingFor;
   }

   @Test
   public void getCorrectAppleResponseWhenGivenExistingModuleId(){
      Integer moduleId = 1;
      String modDesc = "PRINCIPIANTE";

      givenExistingModuleWithApples(moduleId, modDesc);
      AppleResponse response = appleService.getApplesFromMolude(moduleId, 1);
      thenObtainCorrectAppleResponse(2, response.getApples().size());
   }

   @Test
   public void getEmptyAppleResponseWhenGivenNotExistingModuleId(){
      Integer moduleId = 2;
      String modDesc = "PRINCIPIANTE";

      givenExistingModuleWithApples(moduleId, modDesc);
      AppleResponse response = appleService.getApplesFromMolude(moduleId, 1);
      thenObtainCorrectAppleResponse(2, response.getApples().size());
   }

   private void thenObtainCorrectAppleResponse(int expected, int actual) {
      assertEquals(expected, actual);
   }

   private void givenExistingModuleWithApples(int moduleId, String mDescription){
      Module m = new Module(moduleId, mDescription);
      List<Apple> applesOfModule = new ArrayList<Apple>();
      applesOfModule.add(getApple(1, "A", m));
      applesOfModule.add(getApple(2, "E", m));
      when(appleRepository.findAllByModuleId(moduleId)).thenReturn(applesOfModule);
   }

   private void givenNonExistingModuleWithApples(int moduleId, String mDescription){
      Module m = new Module(moduleId, mDescription);
      List<Apple> applesOfModule = new ArrayList<Apple>();
      applesOfModule.add(getApple(1, "A", m));
      applesOfModule.add(getApple(2, "E", m));
      when(appleRepository.findAllByModuleId(moduleId)).thenReturn(applesOfModule);
   }

   private void givenExistingApple(int id, String description) {
      Apple apple = new Apple(id, description);
      when(appleRepository.findById(id)).thenReturn(Optional.of(apple));
   }

   private Apple getApple(int id, String desc, Module mod){
      Apple apple = new Apple(id, desc);
      apple.setModulo(mod);
      return apple;
   }

}
