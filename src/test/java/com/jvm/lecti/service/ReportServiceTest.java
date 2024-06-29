package com.jvm.lecti.service;

import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Module;
import com.jvm.lecti.domain.entity.Result;
import com.jvm.lecti.domain.service.ReportService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;;

import java.io.File;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ReportServiceTest {


   @Mock
   private AppleDAO appleDAO;

   @Mock
   private ResultDAO resultDAO;

   @InjectMocks
   private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
   public void testCreateReport() {
     Map<String, Integer> data = prepareData();
     Integer playerId = 1;
     Integer moduleId = 1;
     List<Result> results = mockResutlList();
     List<Apple> appleListMod1 = mockAppleList(1);  //las pongo aca porque si mando el metodo directo al thenReturn explota por multihilo, preg a Seba
     List<Apple> appleListMod2 = mockAppleList(2);
     List<Apple> appleListMod3 = mockAppleList(3);
     when(resultDAO.findAllByPlayerId(playerId)).thenReturn(results);
     when(appleDAO.findAllByModuleIdOrderByIndex(moduleId)).thenReturn(appleListMod1);
     when(appleDAO.findAllByModuleIdOrderByIndex(moduleId+1)).thenReturn(appleListMod2);
     when(appleDAO.findAllByModuleIdOrderByIndex(moduleId+2)).thenReturn(appleListMod3);
     File report = reportService.createReport(playerId);
     assertNotNull(report);
     assertEquals(true, report.exists());
   }

    private Map<String, Integer> prepareData() {
        Map<String, Integer> data = new HashMap<>();
        data.put("A", 3);
        data.put("B", 2);
        data.put("C", 2);
        data.put("D", 1);
        data.put("E", 3);
        data.put("F", 2);
        data.put("G", 1);
        data.put("H", 3);
        data.put("I", 2);
        data.put("J", 1);
        data.put("K", 3);
        data.put("L", 2);
        data.put("M", 1);
        data.put("N", 3);
        data.put("O", 2);
        data.put("P", 1);
        data.put("Q", 3);
        data.put("R", 2);
        data.put("S", 1);
        data.put("T", 3);
        data.put("U", 2);
        data.put("V", 1);
        data.put("W", 3);
        data.put("X", 2);
        data.put("Y", 1);
        data.put("Z", 3);
        return data;
    }

    private List<Result> mockResutlList(){
       List<Result> results = new ArrayList<>();
       String[] names = new String[]{"A","B","C","D","E","F","G","H","I","J","K","M","L","M","N","O","P","Q","R","S","T"};
       for(int i = 1 ; i<14 ; i++){
           Result result = new Result();
           Apple apple = new Apple();
           apple.setId(i);
           apple.setName(names[i]);
           apple.setModule(new Module(new Random().nextInt(3) + 1, ""));
           result.setApple(apple);
           result.setScore( new Random().nextInt(3) + 1);
           results.add(result);
       }
       return results;
    }

    private List<Apple> mockAppleList(Integer callNumber){
       List<Apple> apples = new ArrayList<>();
       switch (callNumber){
           case 1:
               for(int i = 1 ; i<6 ; i++) {
                   Apple apple = new Apple();
                   apple.setId(i);
                   apple.setName("A");
                   apple.setModule(new Module(1, "BASICO"));
                   apple.setIndex(i);
                   apples.add(apple);
               }
               return apples;
           case 2:
               for(int i = 7 ; i<7+5 ; i++) {
                   Apple apple = new Apple();
                   apple.setId(i);
                   apple.setName("B");
                   apple.setModule(new Module(2, "INTERMEDIO"));
                   apple.setIndex(i);
                   apples.add(apple);
               }
               return apples;
           case 3:
               for(int i = 12 ; i<12+6 ; i++) {
                   Apple apple = new Apple();
                   apple.setId(i);
                   apple.setName("C");
                   apple.setModule(new Module(3, "AVANZADO"));
                   apple.setIndex(i);
                   apples.add(apple);
               }
               return apples;
           default:
               return null;
       }
    }

}

