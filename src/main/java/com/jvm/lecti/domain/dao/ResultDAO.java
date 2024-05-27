package com.jvm.lecti.domain.dao;

import com.jvm.lecti.domain.entity.Result;

import java.util.Optional;

public interface ResultDAO {

    Optional<Result> findAllByAppleAndPlayerId(int appleId, int playerId);
}
