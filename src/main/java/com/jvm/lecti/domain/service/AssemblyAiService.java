package com.jvm.lecti.domain.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;
import com.jvm.lecti.domain.config.Config;

@Service
public class AssemblyAiService {

   private AssemblyAI client;

   @Autowired
   private Config config;

   public AssemblyAiService(Config config) {
      this.config = config;
      this.client = AssemblyAI.builder().apiKey(config.getAssemblyAiKey()).build();
   }

   public Transcript transcribe(File file) throws IOException {
      Transcript transcript = null;
      if (file.exists() || file.canRead()) {
         transcript = client.transcripts().transcribe(file, TranscriptOptionalParams.builder().languageCode(TranscriptLanguageCode.ES).build());
         if (transcript.getStatus().equals(TranscriptStatus.ERROR)) {
            System.err.println(transcript.getError());
         }
      }
      return transcript;
   }

}
