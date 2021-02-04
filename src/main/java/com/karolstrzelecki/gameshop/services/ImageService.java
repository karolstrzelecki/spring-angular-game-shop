package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.models.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.List;

public interface ImageService {

    public void init();

    public String save(MultipartFile file);

    public FileInfo load(String filename);


//    public void deleteAll();
//
    public List<String> loadAll();
}
