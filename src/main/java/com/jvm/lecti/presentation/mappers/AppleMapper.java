package com.jvm.lecti.presentation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jvm.lecti.domain.objects.AppleResultValue;
import com.jvm.lecti.presentation.dto.response.AppleDto;

@Mapper
public interface AppleMapper {

   AppleMapper INSTANCE = Mappers.getMapper(AppleMapper.class);

   List<AppleDto> appleResultListToAppleDtoList(List<AppleResultValue> appleResultValues);

}
