package com.jvm.lecti.service;

import com.jvm.lecti.entity.TestEntity;
import com.jvm.lecti.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;


    public Optional<TestEntity> getTest(Integer id) {
        return testRepository.findById(id);
    }

}
