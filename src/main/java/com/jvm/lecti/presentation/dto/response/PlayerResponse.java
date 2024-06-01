package com.jvm.lecti.presentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlayerResponse {

   private List<PlayerDto> players;

}
