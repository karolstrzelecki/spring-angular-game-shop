package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.models.FileInfo;
import com.karolstrzelecki.gameshop.repository.ComputerGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ComputerGameRepository cgRepo;

    private final Path root = Paths.get("src/main/resources/static/uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    private String generateRandomName() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    @Override
    public String save(MultipartFile file) {
        String fileName;
        try {
            fileName = generateRandomName() + "." + (file.getContentType().split("/"))[1];
            Files.copy(file.getInputStream(), this.root.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return fileName;
    }

        @Override
    public FileInfo load(String filename) {

            FileInfo fi = new FileInfo();


            try {
                Path file = root.resolve(filename);
                byte [] bytePic = Files.readAllBytes(file);
                fi.setName(filename);
                fi.setPicbyte(bytePic);
                fi.setPath(file);
            } catch (IOException e) {
                e.printStackTrace();
            }


            return fi;

        }


//    @Override
//    public Resource load(String filename) {
//        try {
//            Path file = root.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//
//
//    }

    @Override
    public List<String> loadAll() {

        List<String> fileNames = cgRepo.findAll().stream().map(e-> e.getImageUrl()).sequential().flatMap(Collection::stream).collect(Collectors.toList());
//        System.out.println(fileNames);
//
//       List<Path> resources = fileNames.stream().map(e-> {
//            Path file = root.resolve(e);
//                    return file;
//        }).collect(Collectors.toList());

//        try {
//            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//        } catch (IOException e) {
//            throw new RuntimeException("Could not load the files!");
//        }
    return fileNames;
    }


}
