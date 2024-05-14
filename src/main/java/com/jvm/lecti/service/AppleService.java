package com.jvm.lecti.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;
import com.jvm.lecti.repository.AppleRepository;

@Service
public interface AppleService {

   AppleResponse getApple(int id);

   AppleResponse getApplesFromMolude(int moduleId, int playerId);

   List<Apple> getApples(int moduleId, int playerId);

}
