package com.jvm.lecti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.AppleDto;
import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.repository.AppleRepository;

@Service("AppleService")
public class AppleServiceImpl implements AppleService{

   @Autowired
   AppleRepository appleRepository;

   public AppleServiceImpl (AppleRepository appleRepository){
      this.appleRepository = appleRepository;
   }

   @Override
   public AppleResponse getApple(int id) {
      AppleResponse response = new AppleResponse();
      Optional<Apple> apple = appleRepository.findById(id);
      List<Apple> apples = new ArrayList<Apple>();
      apples.add(apple.orElseThrow());
      List<AppleDto> applesDto = mapModuleDto(apples);
      response.setApples(applesDto);
      return response;
   }

   @Override
   public AppleResponse getApplesFromMolude(int moduleId) {
      AppleResponse response = new AppleResponse();

      List<Apple> apples = appleRepository.findAllByModuleId(moduleId);
      List<AppleDto> applesDto = mapModuleDto(apples);
      response.setApples(applesDto);
      return response;
   }

   private List<AppleDto> mapModuleDto(List<Apple> apples) {
      if (apples.isEmpty()) {
         return null;
      }
      List<AppleDto> appleList = new ArrayList<>();
      for (Apple entity : apples) {
         appleList.add(new AppleDto(entity.getId(), entity.getName()));
      }
      return appleList;
   }

}
