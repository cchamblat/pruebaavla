package com.backend.pruebaavla.services.impl;

import com.backend.pruebaavla.models.Deal;
import com.backend.pruebaavla.services.ReportService;
import com.backend.pruebaavla.utils.Utils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static org.apache.poi.ss.usermodel.Row.CREATE_NULL_AS_BLANK;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private Utils utils;

    @Override
    public List<List<Deal>> readFile(MultipartFile file) throws IOException, ParseException {
        DateFormat dateFormatLogDate = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dateFormatLogTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        List<Deal> deals = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        //En el caso que una celda nula, dejar en blanco
        workbook.setMissingCellPolicy(CREATE_NULL_AS_BLANK);
        //Leer la primera hoja del archivo
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            //Agregar cada registro (Deal), a una lista
            Deal deal = new Deal();
            XSSFRow row = worksheet.getRow(i);
            deal.setDealId(String.valueOf((int) row.getCell(0).getNumericCellValue()));
            deal.setUserId(String.valueOf((int) row.getCell(1).getNumericCellValue()));
            deal.setUserName(row.getCell(2).getStringCellValue());
            deal.setFieldKey(row.getCell(3).getStringCellValue());
            deal.setOldValue(row.getCell(4).getStringCellValue());
            deal.setNewValue(row.getCell(5).getStringCellValue());
            deal.setLogDate(dateFormatLogDate.format(row.getCell(7).getDateCellValue()));
            deal.setLogTime(dateFormatLogTime.format(row.getCell(8).getDateCellValue()));
            deal.setChangeSource(row.getCell(9).getStringCellValue());
            deals.add(deal);
        }
        deals.sort(Comparator.comparing(Deal::getDealId));
        List<?> times = utils.getTimes(utils.orderDeals(deals));
        System.out.println(times.toString());
        return utils.orderDeals(deals);
    }












}







