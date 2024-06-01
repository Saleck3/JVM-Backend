package com.jvm.lecti.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jvm.lecti.domain.entity.Player;
import com.jvm.lecti.presentation.dto.response.PlayerSessionResponse;
import com.jvm.lecti.presentation.dto.response.PlayerDto;

@Mapper
public interface PlayerMapper {

   PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

   List<PlayerDto> playerListToPlayerListDto(List<Player> players);

   List<PlayerSessionResponse> playerListToPlayerDataResponseListDto(List<Player> players);

}
