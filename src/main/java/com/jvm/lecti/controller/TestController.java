package com.jvm.lecti.controller;

import com.jvm.lecti.entity.TestEntity;
import com.jvm.lecti.service.TestService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
@NoArgsConstructor
public class TestController {

    @Autowired
    private TestService service;

    @GetMapping
    public Integer test() {
        Optional<TestEntity> test = service.getTest(1);
        return test.map(TestEntity::getId).orElse(0);
    }
}
