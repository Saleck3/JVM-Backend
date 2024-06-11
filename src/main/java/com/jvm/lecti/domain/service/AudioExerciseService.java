package com.jvm.lecti.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.jvm.lecti.domain.dao.ExerciseDAO;
import com.jvm.lecti.domain.objects.AudioExerciseValue;
import com.jvm.lecti.domain.objects.Match;
import com.jvm.lecti.domain.objects.ResultAudio;

@Service("AudioExerciseService")
public class AudioExerciseService {

   @Autowired
   private AssemblyAiService assemblyAiService;

   @Autowired
   private ExerciseDAO exerciseDAO;

   public AudioExerciseService(ExerciseDAO exerciseDAO, AssemblyAiService assemblyAiService) {
      this.assemblyAiService = assemblyAiService;
      this.exerciseDAO = exerciseDAO;
   }

   public AudioExerciseValue checkExercise(MultipartFile mFile, final int exerciseId) {
      int crowns = 3;
      String expectedText = "";
      Transcript transcript;
      ResultAudio ra = new ResultAudio();
      try {
         expectedText = JsonParser.parseString(exerciseDAO.findById(exerciseId).get().getParameters()).getAsJsonObject().get("correctAnswer").getAsString();
         transcript = assemblyAiService.trasncribe(convertToFile(mFile, "exercise" + ".mp3"));
         ra = processResult(transcript, expectedText);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return AudioExerciseValue.builder().score(crowns).isCorrect(ra.getIsCorrect()).corrections(ra.getCorrectionText()).build();
   }

   private ResultAudio processResult(Transcript t, String expectedText) {
      Match ma;
      ma = matchResults(t, expectedText);
      return getResult(ma);
   }

   private ResultAudio getResult(Match ma) {
      ResultAudio ra = new ResultAudio();
      if (ma.textMatch() || ma.getMatchWords().size() == ma.getExpectedWords().size()) {
         return new ResultAudio(true);
      }
      ra.processRetry(ma);
      return ra;
   }

   private Match matchResults(Transcript t, String expectedText) {
      Match match = new Match(t, expectedText);
      if (match.textMatch()) {
         return match;
      } else {
         fillMatchWords(match);
      }
      return match;
   }

   private void fillMatchWords(Match match) {
      for (String ew : match.getExpectedWords()) {
         if (match.getTranscriptWordsValue().contains(ew)) {
            match.addWordToList(ew, 1);   //agrego a la lista de matcheados
         } else {
            match.addWordToList(ew, 2);   //agrego a la lista de palabras esperadas faltantes
         }
      }
      for (String tw : match.getTranscriptWordsValue()) {
         if (!match.getExpectedWords().contains(tw)) {
            match.addWordToList(tw, 3);   //agrego a la lista de palabras extra que dijo
         }
      }
   }

   private File convertToFile(MultipartFile file, String fileName) throws IOException {
      File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
      convFile.createNewFile();
      try (FileOutputStream fos = new FileOutputStream(convFile)) {
         fos.write(file.getBytes());
      }
      return convFile;
   }

}
