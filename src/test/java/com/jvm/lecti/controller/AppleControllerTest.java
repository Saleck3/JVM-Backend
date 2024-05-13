package com.jvm.lecti.controller;

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
import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.service.AppleService;
import com.jvm.lecti.service.AppleServiceTest;



@SpringBootTest
public class AppleControllerTest {

   private AppleController appleController;

   private AppleService appleService;

   @Before
   public void init() {
      appleService = mock(AppleService.class);
      appleController = new AppleController(appleService);
   }

   @Test
   public void ifIdDoNotExistReturnErrorResponse() {
      Integer id = 0;
      givenApple(id);
      AppleResponse appleRsp = whenAskingForApple(id);
      assertEquals(0, appleRsp.getApples().size());
   }

   @Test
   public void ifAppleIdExistReturnCorrectResponse() {
      Integer id = 1;
      givenApple(id);
      AppleResponse appleRsp = whenAskingForApple(id);
      assertEquals(appleRsp, id);
   }

   private AppleResponse whenAskingForApple(int id) {
      return appleController.getApple(id);
   }

   private void givenApple(int id){
      if(id == 0){
         List<AppleDto> appleList = new ArrayList<AppleDto>();
         AppleResponse appleRes = new AppleResponse();
         appleRes.setApples(appleList);
         when(appleService.getApple(id)).thenReturn(appleRes);
      } else {
         AppleDto appleDto = new AppleDto(id, "MA");
         List<AppleDto> appleList = new ArrayList<AppleDto>();
         appleList.add(appleDto);
         AppleResponse appleRes = new AppleResponse();
         appleRes.setApples(appleList);
         when(appleService.getApple(id)).thenReturn(appleRes);
      }

   }



}


