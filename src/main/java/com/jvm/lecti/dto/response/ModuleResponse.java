package com.jvm.lecti.dto.response;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ModuleResponse {
   public List<ModuleDto> modules;
}
