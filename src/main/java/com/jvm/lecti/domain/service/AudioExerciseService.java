package com.jvm.lecti.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptLanguageCode;
import com.assemblyai.api.resources.transcripts.types.TranscriptOptionalParams;
import com.assemblyai.api.resources.transcripts.types.TranscriptStatus;
import com.jvm.lecti.domain.dao.ExerciseDAO;
import com.jvm.lecti.domain.entity.Exercise;
import com.jvm.lecti.domain.objects.AudioExerciseValue;
import com.jvm.lecti.domain.objects.Match;
import com.jvm.lecti.domain.objects.ResultAudio;

@Service("AudioExerciseService")
public class AudioExerciseService {

   @Autowired
   AssemblyAiService as;

   @Autowired
   ExerciseDAO exDAO;

   public AudioExerciseService(ExerciseDAO exDAO, AssemblyAiService assemblyAiService) {
      as = assemblyAiService;
      this.exDAO = exDAO;
   }

   public AudioExerciseValue checkExercise(MultipartFile mFile, final int exerciseId){
      int crowns = 3;
      String expectedText = "";
      Transcript transcript;
      ResultAudio ra = new ResultAudio();
      try {
         expectedText = exDAO.findById(exerciseId).get().getParameters();
         transcript = as.trasncribe(convertToFile(mFile, "exercise" + ".mp3"));
         ra = processResult(transcript, expectedText);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return AudioExerciseValue.builder().crowns(crowns).isCorrect(ra.getIsCorrect()).corrections(ra.getCorrectionText()).build();
   }

   private ResultAudio processResult(Transcript t, String expectedText) {
      Match ma;
      ma = matchResults(t, expectedText);
      return getResult(ma);
   }

   private ResultAudio getResult(Match ma) {
      ResultAudio ra = new ResultAudio();
      String correctionMessage = "";
      if(ma.textMatch()){
         return new ResultAudio(true);
      }
//      if(ma.getMatchWords().size() < ma.getExpectedWords().size()){
//         for(String ew : ma.getExpectedWords()){
//            if(ma.getMatchWords().contains(ew)){
//               ra.addWordToCorrectionMessage(ew,true);
//            } else {
//               ra.addWordToCorrectionMessage(ew,false);
//            }
//         }
//      }
      ra.processRetry(ma);
      return ra;
   }

   private Match matchResults(Transcript t, String expectedText) {
      Match match = new Match(t, expectedText);
      if(match.textMatch()){
         return match;
      } else {
         fillMatchWords(match);
      }
      return match;
   }

   private void fillMatchWords(Match match) {
      List<Boolean> wordsMatch = new ArrayList<Boolean>();
      for (String ew : match.getExpectedWords()){
         if(match.getTranscriptWordsValue().contains(ew)){
            match.addWordToList(ew, 1);   //agrego a la lista de matcheados
         } else {
            match.addWordToList(ew, 2);   //agrego a la lista de palabras esperadas faltantes
         }
      }
      for (String tw : match.getTranscriptWordsValue()){
         if(!match.getExpectedWords().contains(tw)) {
            match.addWordToList(tw, 3);   //agrego a la lista de palabras extra que dijo
         }
      }
   }

//   private String delSpecChars(String word){
//      String finalWord = word;
//      char[] delSpeCha = word.toCharArray();
//      for(int i = 0 ; i < delSpeCha.length; i++){
//         char c = delSpeCha[i];
//         //         if(!Character.isAlphabetic(c)){
//         finalWord = word.replaceAll("[^\\p{L} ]", "");
//         //         }
//      }
//      return finalWord;
//   }

   private File convertToFile(MultipartFile file, String fileName) throws IOException {
      File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
      convFile.createNewFile();
      try (FileOutputStream fos = new FileOutputStream(convFile)) {
         fos.write(file.getBytes());
      }
      return convFile;
   }
}
