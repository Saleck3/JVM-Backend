package com.jvm.lecti.presentation.dto.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

   public HttpStatus httpStatus;

   public String message;

}
