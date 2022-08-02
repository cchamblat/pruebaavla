package com.backend.pruebaavla.services;

import com.backend.pruebaavla.models.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReportService {
    List<Item> readFile(MultipartFile file);
}
