package com.jvm.lecti.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jvm.lecti.domain.entity.Apple;

@Service
public interface AppleService {

   Optional<Apple> getApple(int playerId, int id);

   List<Apple> getApples(int moduleId, int playerId);

}
