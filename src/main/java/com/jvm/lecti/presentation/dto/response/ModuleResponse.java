package com.jvm.lecti.presentation.dto.response;

import java.util.List;

import com.jvm.lecti.domain.entity.Apple;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModuleResponse {

   private Integer id;
   private String description;
   private List<Apple> appleList;

}
