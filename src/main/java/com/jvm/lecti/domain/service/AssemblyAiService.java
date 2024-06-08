package com.jvm.lecti.domain.service;
import java.io.File;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;
import com.jvm.lecti.domain.config.Config;
import com.jvm.lecti.domain.objects.Match;
import com.jvm.lecti.domain.objects.ResultAudio;

@Service
public class AssemblyAiService {

   AssemblyAI client;

   @Autowired
   Config config;

   public AssemblyAiService(Config config) {
      this.config = config;
      client = AssemblyAI.builder().apiKey(config.getAssemblyAiKey()).build();
      System.out.println("llego");
   }

   public Transcript trasncribe(File f){
      Transcript transcript = null;
      try{
         if(f.exists() || f.canRead()) {
               transcript = client.transcripts().transcribe(f,
                     TranscriptOptionalParams
                     .builder()
                     .languageCode(TranscriptLanguageCode.ES)
   //                  .speechModel(SpeechModel.NANO)
                     .build()
                  );

            if (transcript.getStatus().equals(TranscriptStatus.ERROR)) {
               System.err.println(transcript.getError());
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      } catch (Exception e){
         e.printStackTrace();
      }

      return transcript;
   }



}
