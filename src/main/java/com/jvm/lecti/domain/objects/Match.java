package com.jvm.lecti.domain.objects;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.assemblyai.api.resources.transcripts.types.Transcript;
import com.assemblyai.api.resources.transcripts.types.TranscriptWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Match {

   private Transcript transcript;

   private String expectedText;

   private List<String> matchWords;

   private List<String> unmatchExpectedWords;

   private List<String> unmatchTranscriptWords;

   public Match(Transcript t, String expectedText) {
      transcript = t;
      this.expectedText = expectedText.toLowerCase();
      matchWords = new ArrayList<>();
      unmatchExpectedWords = new ArrayList<>();
      unmatchTranscriptWords = new ArrayList<>();
   }

   public boolean textMatch() {
      String transText = transcript.getText().get();
      transText = normalizeAndRemoveSpecialChars(transText);
      return transText.equalsIgnoreCase(expectedText);
   }

   public List<String> getExpectedWords() {
      List<String> expWords = new ArrayList<>();
      for (String ew : Arrays.asList(expectedText.split(" "))) {
         expWords.add(normalizeAndRemoveSpecialChars(ew));
      }
      return expWords;
   }

   public List<TranscriptWord> getTranscriptWords() {
      return transcript.getWords().get();
   }

   public List<String> getTranscriptWordsValue() {
      List<String> twv = new ArrayList<String>();
      for (TranscriptWord tw : getTranscriptWords()) {
         String normalizeTW = normalizeAndRemoveSpecialChars(tw.getText()).toLowerCase();
         if (!twv.contains(normalizeTW)) {
            twv.add(normalizeTW);
         }
      }
      return twv;
   }

   public void addWordToList(String word, int list) { //1 match, 2 unmatchExpected, 3 unmatchedTranscript
      switch (list) {
         case 1:
            matchWords.add(word);
            break;
         case 2:
            unmatchExpectedWords.add(word);
            break;
         case 3:
            unmatchTranscriptWords.add(word);
      }
   }

   private static String normalizeAndRemoveSpecialChars(String str) {
      // Normalizar la cadena
      String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
      // Eliminar caracteres diacríticos (acentos)
      normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
      // Eliminar caracteres que no sean letras ni espacios
      normalized = normalized.replaceAll("[^\\p{L} ]", "");
      return normalized;
   }

   public boolean expectedIsAWord() {
      return getExpectedWords().size() == 1 ? true : false;
   }

}
