package com.backend.pruebaavla.services;

import com.backend.pruebaavla.models.Deal;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReportService {
    List<Deal> readFile(MultipartFile file) throws IOException;
}
