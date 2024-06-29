package com.jvm.lecti.domain.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jvm.lecti.domain.dao.AppleDAO;
import com.jvm.lecti.domain.dao.ResultDAO;
import com.jvm.lecti.domain.entity.Apple;
import com.jvm.lecti.domain.entity.Result;
import org.jetbrains.annotations.NotNull;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ReportService {

    @Autowired
    ResultDAO resultDAO;

    @Autowired
    AppleDAO appleDAO;

    public File createReport(Integer playerId){
        Map<String, Integer> resultData = new HashMap<String, Integer>();
        Map<String, Double[]> moduleMetrics = new HashMap<String, Double[]>();
        AtomicInteger lowestScoreModule = new AtomicInteger(0);
        prepareDataSets(playerId, resultData, moduleMetrics, lowestScoreModule);
        try{
            return createReportWithTable(resultData, moduleMetrics, lowestScoreModule.get());
        } catch (Exception e){
            return null;
        }
    }

    private void prepareDataSets(Integer playerId, Map<String, Integer> resultData, Map<String, Double[]> moduleMetrics, AtomicInteger lowestScoreModule) {
        List<Result> playerResults = new ArrayList<>();
        List<Integer> resultAppleIds = new ArrayList<>();
        List<Apple> applesBasicModule = new ArrayList<>();
        List<Apple> applesMediumModule = new ArrayList<>();
        List<Apple> applesAdvancedModule = new ArrayList<>();

        fillResultData(playerResults, resultData, playerId, resultAppleIds);
        fillAppleData(moduleMetrics, resultAppleIds, applesBasicModule, applesMediumModule, applesAdvancedModule);
        fillLowestModule(lowestScoreModule, playerResults);
    }

    private void fillLowestModule(AtomicInteger lowestScoreModule, List<Result> playerResults) {
        Integer totScoreBasicMod = 0;
        Integer totScoreMediumMod = 0;
        Integer totScoreAdvancedMod = 0;

        for(Result r : playerResults){
            Integer resultModule = r.getApple().getModule().getId();
            switch (resultModule){
                case 1:
                    totScoreBasicMod += r.getScore();
                    break;
                case 2:
                    totScoreMediumMod += r.getScore();
                    break;
                case 3:
                    totScoreAdvancedMod += r.getScore();
                    break;
            }
        }
        Integer maxValue = Collections.max(Arrays.asList(totScoreBasicMod, totScoreMediumMod, totScoreAdvancedMod));
        if(maxValue==totScoreBasicMod){
            lowestScoreModule.set(1);
        } if(maxValue==totScoreMediumMod){
            lowestScoreModule.set(2);
        } else {
            lowestScoreModule.set(3);
        }
    }

    private void fillAppleData( Map<String, Double[]> moduleMetrics, List<Integer> resultAppleIds,
            List<Apple> applesBasicModule, List<Apple> applesMediumModule, List<Apple> applesAdvancedModule) {
        List<Apple> applesBasicModuleTemp = appleDAO.findAllByModuleIdOrderByIndex(1);
        List<Apple> applesMediumModuleTemp = appleDAO.findAllByModuleIdOrderByIndex(2);
        List<Apple> applesAdvancedModuleTemp = appleDAO.findAllByModuleIdOrderByIndex(3);

        //ESTO ES PORQUE SINO SE SOBRESCRIBE LA REFERENCIA EN EL HEAP
        applesBasicModule.addAll(applesBasicModuleTemp);
        applesMediumModule.addAll(applesMediumModuleTemp);
        applesAdvancedModule.addAll(applesAdvancedModuleTemp);

        Double totalApplesBasicModule = (double) applesBasicModuleTemp.size();
        Double totalApplesMediumModule = (double) applesMediumModuleTemp.size();
        Double totalApplesAdvancedModule = (double) applesAdvancedModuleTemp.size();

        List<Integer> basicModuleAppleIds = applesBasicModuleTemp.stream()
                .map(apple -> apple.getId()).collect(Collectors.toList());
        List<Integer> mediumModuleAppleIds = applesMediumModuleTemp.stream()
                .map(apple -> apple.getId()).collect(Collectors.toList());
        List<Integer> advancedModuleAppleIds = applesAdvancedModuleTemp
                .stream().map(apple -> apple.getId()).collect(Collectors.toList());

        Double completedCantBasicMod = 0.0;
        Double completedCantMediumMod = 0.0;
        Double completedCantAdvancecMod = 0.0;

        for(Integer appleId : basicModuleAppleIds){
            if(resultAppleIds.contains(appleId)){
                completedCantBasicMod++;
            }
        }

        for(Integer appleId : mediumModuleAppleIds){
            if(resultAppleIds.contains(appleId)){
                completedCantMediumMod++;
            }
        }

        for(Integer appleId : advancedModuleAppleIds){
            if(resultAppleIds.contains(appleId)){
                completedCantAdvancecMod++;
            }
        }

        moduleMetrics.put("BASIC", new Double[]{totalApplesBasicModule, completedCantBasicMod});
        moduleMetrics.put("MEDIUM", new Double[]{totalApplesMediumModule, completedCantMediumMod});
        moduleMetrics.put("ADVANCED", new Double[]{totalApplesAdvancedModule, completedCantAdvancecMod});
    }

    public File createReportWithTable(Map<String, Integer> resultData, Map<String, Double[]> moduleMetrics, Integer lowestSoreModule){
        Document document = new Document();
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 12);
        String path = "./folder/iTexHelloWorld.pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            AddAppleTable(document, resultData, font);
            document.newPage();
            addGraphics(document, moduleMetrics);
            document.newPage();
            addSpacing(document);
            addInfoSection(document, font, lowestSoreModule);
            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(path);
    }

    private void addSpacing(Document document) throws DocumentException {
        Paragraph spacing = new Paragraph(" ");
        spacing.setSpacingBefore(20f);
        spacing.setSpacingAfter(20f);
        document.add(spacing);
    }

    private void addInfoSection(Document document, Font font, Integer lowestScoreModule) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        Stream.of("Modulo con Mejor Puntaje", "2")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        document.add(table);
    }

    private void AddAppleTable(Document document, Map<String, Integer> resultData, Font font) throws DocumentException {
        document.open();
        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table, resultData, font);
        document.add(table);
    }

    private void addGraphics(Document document, Map<String, Double[]> modulesMetrics) throws IOException, DocumentException {
        DefaultPieDataset datasetBeginner = new DefaultPieDataset();
        DefaultPieDataset datasetMedium = new DefaultPieDataset();
        DefaultPieDataset datasetAdvance =  new DefaultPieDataset();

        preparePieDataSets(modulesMetrics, datasetBeginner, datasetMedium, datasetAdvance);

        document.add(createGraphicAndImage("Modulo Principiante", datasetBeginner));
        document.add(createGraphicAndImage("Modulo Intermedio", datasetMedium));
        document.add(createGraphicAndImage("Modulo Avanzado", datasetAdvance));
    }

    private void preparePieDataSets(Map<String, Double[]> modulesMetrics, DefaultPieDataset datasetBeginner, DefaultPieDataset datasetMedium, DefaultPieDataset datasetAdvance) {
        List<Double[]> moduleValues = getListFromMap(modulesMetrics);
        getDataset(moduleValues.get(0)[0], moduleValues.get(0)[1], datasetBeginner);
        getDataset(moduleValues.get(1)[0], moduleValues.get(1)[1], datasetMedium);
        getDataset(moduleValues.get(2)[0], moduleValues.get(2)[1], datasetAdvance);
    }

    private Image createGraphicAndImage(String title, DefaultPieDataset dataset) throws IOException, BadElementException {
        // Crear el gráfico de torta
        JFreeChart pieChart = ChartFactory.createPieChart(title, dataset, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setSectionOutlinesVisible(false);

        // Convertir el gráfico a un byte array
        ByteArrayOutputStream chartOutputStream = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(chartOutputStream, pieChart, 500, 300);
        byte[] chartBytes = chartOutputStream.toByteArray();

        // Crear Image a partir del byte array
        Image chartImage = Image.getInstance(chartBytes);
        chartImage.setAlignment(Element.ALIGN_CENTER);
        return chartImage;
    }

    private void getDataset(double totalModuleApples, double completedApples, DefaultPieDataset dataSet) {
        double completedPorcent = (completedApples / totalModuleApples ) * 100;
        dataSet.setValue("Manzanas Completas", (int)completedPorcent);
        dataSet.setValue("Manzanas Incompletas", 100-((int)completedPorcent));
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Manzana", "Puntaje")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Map<String, Integer> data, Font font) {
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String apple = entry.getKey();
            Integer score = entry.getValue();
            // Crear la celda para "apple"
            PdfPCell appleCell = new PdfPCell();
            appleCell.setPhrase(new com.itextpdf.text.Phrase(apple));
            table.addCell(appleCell);

            // Crear la celda para "score" con estrellas
            PdfPCell scoreCell = new PdfPCell();
            //Phrase phrase = new Phrase(getStars(score), font);
            Paragraph p = new Paragraph(getStars(score));
            p.setFont(font);
            scoreCell.addElement(p);
            scoreCell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            table.addCell(score.toString());
        }
    }

    public static String getStars(int score) {
        String stars = "";
        for (int i = 0; i < score; i++) {
            stars += '\u2605' ; // Unicode estrella negra
        }
        return stars.toString();
    }

    public static List<Integer> getAppleIdsFromResults(List<Result> results) {
        return results.stream()
                .map(result -> result.getApple().getId())
                .collect(Collectors.toList());
    }

    public void fillResultData(List<Result> playerResults, Map<String, Integer> resultData, final Integer playerId, List<Integer> resultAppleIds) {
        List<Result> tempResults = resultDAO.findAllByPlayerId(playerId);
        Map<String, Integer> resultDataTemp = new HashMap<>();
        if (!tempResults.isEmpty()) {
            playerResults.addAll(tempResults);
            for (Result result : tempResults) {
                resultDataTemp.put(result.getApple().getName(), result.getScore());
            }
            resultAppleIds.addAll(getAppleIdsFromResults(tempResults));
            resultData.putAll(resultDataTemp);
        }
    }


    public static List<Double[]> getListFromMap(Map<String, Double[]> map) {
        List<Double[]> list = new ArrayList<>();
        list.add(new Double[]{map.get("BASIC")[0],map.get("BASIC")[1]});
        list.add(new Double[]{map.get("MEDIUM")[0],map.get("MEDIUM")[1]});
        list.add(new Double[]{map.get("ADVANCED")[0],map.get("ADVANCED")[1]});
        return list;
    }

}
