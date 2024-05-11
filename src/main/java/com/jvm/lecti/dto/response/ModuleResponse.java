package com.jvm.lecti.dto.response;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModuleResponse {
   private List<ModuleDto> modules = new ArrayList<>();

   public void addModule(ModuleDto module) {
      this.modules.add(module);
   }
}
