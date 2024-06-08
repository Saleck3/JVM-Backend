package com.jvm.lecti.domain.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

   @Value("${assemblyAi.secret}")
   private String assemblyAiKey;

   public String getAssemblyAiKey(){
      return assemblyAiKey;
   }
}
