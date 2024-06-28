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

   public AudioExerciseValue checkExercise(MultipartFile mFile, int exerciseId) throws IOException {
      int crowns = 3;
      String expectedText = "";
      Transcript transcript;
      ResultAudio resultAudio;
      expectedText = JsonParser
            .parseString(exerciseDAO.findById(exerciseId).get().getParameters())
            .getAsJsonObject()
            .get("correctAnswer")
            .getAsString();
      transcript = assemblyAiService.transcribe(convertToFile(mFile, "exercise" + ".mp3"));
      resultAudio = processResult(transcript, expectedText);

      return AudioExerciseValue.builder().score(crowns).isCorrect(resultAudio.getIsCorrect()).corrections(resultAudio.getCorrectionText()).build();
   }

   private ResultAudio processResult(Transcript transcript, String expectedText) {
      Match match = matchResults(transcript, expectedText);
      return getResult(match);
   }

   private ResultAudio getResult(Match match) {
      ResultAudio resultAudio = new ResultAudio();
      if (match.textMatch() || match.getMatchWords().size() == match.getExpectedWords().size()) {
         return new ResultAudio(true);
      }
      resultAudio.processRetry(match);
      return resultAudio;
   }

   private Match matchResults(Transcript transcript, String expectedText) {
      Match match = new Match(transcript, expectedText);
      if (match.textMatch()) {
         return match;
      } else {
         fillMatchWords(match);
      }
      return match;
   }

   private void fillMatchWords(Match match) {
      for (String expectedWord : match.getExpectedWords()) {
         if (match.getTranscriptWordsValue().contains(expectedWord)) {
            match.addWordToList(expectedWord, 1);   //agrego a la lista de matcheados
         } else {
            match.addWordToList(expectedWord, 2);   //agrego a la lista de palabras esperadas faltantes
         }
      }
      for (String transcriptWord : match.getTranscriptWordsValue()) {
         if (!match.getExpectedWords().contains(transcriptWord)) {
            match.addWordToList(transcriptWord, 3);   //agrego a la lista de palabras extra que dijo
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
