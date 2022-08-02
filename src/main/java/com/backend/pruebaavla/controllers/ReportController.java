package com.backend.pruebaavla.controllers;

import com.backend.pruebaavla.models.Item;
import com.backend.pruebaavla.services.impl.ReportServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @PostMapping(value = "report",  consumes = {"multipart/form-data"})
    public List<Item> createReport(@RequestParam("file") MultipartFile file){
        return reportService.readFile(file);


    }





}
