package com.jvm.lecti.service;

import java.util.List;
import java.util.Optional;

import com.jvm.lecti.exceptions.InvalidUserIdForPlayerException;

import org.springframework.stereotype.Service;

import com.jvm.lecti.dto.response.AppleResponse;
import com.jvm.lecti.entity.Apple;

@Service
public interface AppleService {

   Optional<Apple> getApple(int playerId, int id);

   List<Apple> getApples(int moduleId, int playerId);

}
