package com.jvm.lecti.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

   //TODO chequear si tiene que estar aca o en la clase player que es la unica que lo necesita
   public static Date stringToDate(String fechaString, String formato) {
      SimpleDateFormat sdf = new SimpleDateFormat(formato);
      try {
         return sdf.parse(fechaString);
      } catch (ParseException pe) {
         return new Date();
      }
   }

}
