package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Apple;

import java.util.List;
import java.util.Optional;

public interface AppleDAO {

   List<Apple> findAllById(Integer id);

   List<Apple> findAllByModuleIdOrderByIndex(Integer moduleId);

   List<Integer> findAllIdByModuleId(Integer moduleId);


   Optional<Apple> findById(Integer id);

}
