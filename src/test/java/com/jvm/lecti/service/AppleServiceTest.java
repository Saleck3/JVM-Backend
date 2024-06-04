package com.jvm.lecti.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.domain.service.AppleService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Module;

@SpringBootTest
public class AppleServiceTest {

   private AppleService appleService;

   private AppleDAO appleDAO;

   private ResultDAO resultDAO;

   @Before
   public void init() {
      appleDAO = mock(AppleDAO.class);
      resultDAO = mock(ResultDAO.class);
      appleService = new AppleService(appleDAO, resultDAO);
   }

   @Test
   public void testGetAppleById() {
      Integer id = 1;
      Apple apple = new Apple();
      apple.setId(id);
   }

   @Test
   public void getCorrectAppleDtoWhenGivenExistingAppleId() {
      Integer appleId = 1;
      String description = "MA";
      givenExistingApple(appleId, description);
      //      whenAskingFor;
   }

   @Test
   public void getCorrectAppleResponseWhenGivenExistingModuleId() {
      Integer moduleId = 1;
      String modDesc = "PRINCIPIANTE";
      String email = "test";
      givenExistingModuleWithApples(moduleId, modDesc);
      try {
         List<AppleResultValue> apples = appleService.getAppleResultWithPlayerCrowns(moduleId, 1);
         thenObtainCorrectAppleResponse(2, apples.size());
      } catch (Exception e) {
      }
   }

   @Test
   public void getEmptyAppleResponseWhenGivenNotExistingModuleId() {
      Integer moduleId = 2;
      String modDesc = "PRINCIPIANTE";
      String email = "test";
      givenExistingModuleWithApples(moduleId, modDesc);
      try {
         List<AppleResultValue> apples = appleService.getAppleResultWithPlayerCrowns(moduleId, 1);
         thenObtainCorrectAppleResponse(2, apples.size());
      } catch (Exception e) {
      }
   }

   private void thenObtainCorrectAppleResponse(int expected, int actual) {
      assertEquals(expected, actual);
   }

   private void givenExistingModuleWithApples(int moduleId, String mDescription) {
      Module m = new Module(moduleId, mDescription);
      List<Apple> applesOfModule = new ArrayList<Apple>();
      applesOfModule.add(getApple(1, "A", m));
      applesOfModule.add(getApple(2, "E", m));
      when(appleDAO.findAllByModuleIdOrderByIndex(moduleId)).thenReturn(applesOfModule);
   }

   private void givenNonExistingModuleWithApples(int moduleId, String mDescription) {
      Module m = new Module(moduleId, mDescription);
      List<Apple> applesOfModule = new ArrayList<Apple>();
      applesOfModule.add(getApple(1, "A", m));
      applesOfModule.add(getApple(2, "E", m));
      when(appleDAO.findAllByModuleIdOrderByIndex(moduleId)).thenReturn(applesOfModule);
   }

   private void givenExistingApple(int id, String description) {
      Apple apple = new Apple(id, description);
      when(appleDAO.findById(id)).thenReturn(Optional.of(apple));
   }

   private Apple getApple(int id, String desc, Module mod) {
      Apple apple = new Apple(id, desc);
      apple.setModule(mod);
      return apple;
   }

}
