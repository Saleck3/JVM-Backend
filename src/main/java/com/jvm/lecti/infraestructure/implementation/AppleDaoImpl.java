package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.infraestructure.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppleDaoImpl implements AppleDAO {

    @Autowired
    AppleRepository appleRepository;

    public AppleDaoImpl(AppleRepository appleRepository){
        appleRepository = appleRepository;
    }

    @Override
    public List<Apple> findAllById(Integer id) {
        return appleRepository.findAllById(id);
    }

    @Override
    public List<Apple> findAllByModuleId(int moduleId) {
        return appleRepository.findAllByModuleId(moduleId);
    }

    @Override
    public Optional<Apple> findById(Integer id) {
        return appleRepository.findById(id);
    }
}
