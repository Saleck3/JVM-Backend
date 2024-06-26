package com.jvm.lecti.domain.objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AudioExerciseValue {

   private Integer score;

   private boolean isCorrect;

   private String corrections;

}
