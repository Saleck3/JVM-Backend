package com.jvm.lecti.domain.objects;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultAudio {

   private String correctAnswerText = "¡Muy Bien!";

   private String correctionText = "";

   private Boolean isCorrect;

   private Map<String, String> correctionByWord;

   private String correctionDesc;

   public ResultAudio(boolean isCorrect) {
      this.isCorrect = isCorrect;
      this.correctionByWord = new HashMap<>();
      this.correctionDesc = "";
      if (isCorrect) {
         this.correctionText = correctAnswerText;
      }
   }

   public void processRetry(Match ma) {
      this.isCorrect = false;
      this.correctionText = getFeedback(ma);
   }

   private String getFeedback(Match ma) {
      StringBuilder feedback = new StringBuilder();
//      if (ma.expectedIsAWord()) {
//         if (ma.getUnmatchExpectedWords().isEmpty()) {
//            feedback.append("Buen trabajo! Dijiste la palabra correctamente.");
//         } else {
//            feedback.append("Casi lo tienes! Intentemos decir la palabra de nuevo: ");
//            feedback.append(String.join(", ", ma.getUnmatchExpectedWords()));
//            feedback.append(".");
//         }
//      } else {
//         feedback.append("Buen trabajo! Lo estás haciendo muy bien.");
//
//         if (!ma.getMatchWords().isEmpty()) {
//            feedback.append(" Dijiste correctamente estas palabras: ");
//            feedback.append(String.join(", ", ma.getMatchWords()));
//            feedback.append(".");
//         }
//
//         if (!ma.getUnmatchExpectedWords().isEmpty()) {
//            feedback.append(" Intentemos de nuevo con estas palabras: ");
//            feedback.append(String.join(", ", ma.getUnmatchExpectedWords()));
//            feedback.append(".");
//         }
//
//      }
      feedback.append("No entendí estas palabras: ");
      for(String word : ma.getUnmatchExpectedWords()){
         feedback.append(word+", ");
      }
      feedback.delete(feedback.length() - 2, feedback.length());
      feedback.append(".");
      feedback.append("\n");
      feedback.append("Inténtalo de nuevo con:");
      feedback.append("\n");
      feedback.append(ma.getExpectedText());


      return escapeSpecialCharacters(feedback.toString());
   }

   public static String escapeSpecialCharacters(String input) {
      String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
      Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
      return pattern.matcher(normalized).replaceAll("");
   }

}
