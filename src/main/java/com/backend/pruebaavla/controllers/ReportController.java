package com.backend.pruebaavla.controllers;

import com.backend.pruebaavla.models.Deal;
import com.backend.pruebaavla.services.impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @PostMapping(value = "/updateReport")
    public List<List<Deal>> createReport(@RequestParam("file") MultipartFile file) throws IOException {
        return reportService.readFile(file);
    }





}
