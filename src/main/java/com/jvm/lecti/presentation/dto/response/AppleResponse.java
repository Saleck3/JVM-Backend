package com.jvm.lecti.presentation.dto.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AppleResponse {

   private List<AppleDto> apples;

}
