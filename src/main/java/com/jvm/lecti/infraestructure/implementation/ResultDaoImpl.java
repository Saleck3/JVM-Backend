package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.infraestructure.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ResultDaoImpl implements ResultDAO {

    @Autowired
    private ResultRepository resultRepository;

    public ResultDaoImpl(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    @Override
    public Optional<Result> findAllByAppleAndPlayerId(int appleId, int playerId) {
        return resultRepository.findAllByAppleAndPlayerId(appleId, playerId);
    }
}
