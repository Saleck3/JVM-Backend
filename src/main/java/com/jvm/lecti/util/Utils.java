package com.jvm.lecti.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static Date stringToDate(String fechaString, String formato)  {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        try{
            return sdf.parse(fechaString);
        } catch(ParseException pe){
            return new Date();
        }
    }




}
