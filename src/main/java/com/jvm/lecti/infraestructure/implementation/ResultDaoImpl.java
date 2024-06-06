package com.jvm.lecti.infraestructure.implementation;

import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.infraestructure.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResultDaoImpl implements ResultDAO {

    @Autowired
    private ResultRepository resultRepository;

    public ResultDaoImpl(ResultRepository resultRepository){
        this.resultRepository = resultRepository;
    }

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Optional<Result> findByAppleIdAndPlayerId(Integer appleId, Integer playerId) {
        return resultRepository.findByAppleIdAndPlayerId(appleId, playerId);
    }

    @Override
    public Integer findTotalScoreByAppleIdAndPlayerId(List<Integer> appleIds, Integer playerId) {
        return resultRepository.findTotalScoreByAppleIdAndPlayerId(appleIds,playerId);
    }

}
