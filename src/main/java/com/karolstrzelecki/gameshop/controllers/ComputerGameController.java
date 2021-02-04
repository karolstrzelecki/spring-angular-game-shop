package com.karolstrzelecki.gameshop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karolstrzelecki.gameshop.daos.CgFormValues;
import com.karolstrzelecki.gameshop.dtos.ComputerGameRegisterDto;
import com.karolstrzelecki.gameshop.dtos.TitleListingValue;
import com.karolstrzelecki.gameshop.models.FileInfo;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Language;
import com.karolstrzelecki.gameshop.payload.response.MessageResponse;
import com.karolstrzelecki.gameshop.services.ComputerGameService;
import com.karolstrzelecki.gameshop.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cg")
public class ComputerGameController {

    @Autowired
    ComputerGameService service;

    @Autowired
    ImageService imageService;


    @GetMapping("/save")
    public CgFormValues getUsers() {
        System.out.println("getting");
        CgFormValues cg = new CgFormValues();
        List<GameCategory> categories = Arrays.asList(GameCategory.values());
        List<String> stringCategories = categories.stream().map(e -> e.name).collect(Collectors.toList());
        cg.categories = stringCategories;
        List<Language> languages = Arrays.asList(Language.values());
        List<String> stringLanguages = languages.stream().map(e -> e.description).collect(Collectors.toList());
        cg.languages = stringLanguages;

        return cg;
    }

//    @PostMapping(value = "/save")
//    public ResponseEntity<?> registerGame(@RequestBody ComputerGameRegisterDto computerGameRegisterDto){

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<?> registerGame(@RequestPart("game") String computerGameRegisterDto,
                                          @RequestPart("imageFile") MultipartFile[] files) throws IOException, ParseException {

     ComputerGameRegisterDto cgDto = new ObjectMapper().readValue(computerGameRegisterDto, ComputerGameRegisterDto.class);

        System.out.println("Jestem w kontrolerze");
//        List<GameCategory> categories = Arrays.asList(GameCategory.values());
//        List<String> stringCategories = categories.stream().map(e -> e.name).collect(Collectors.toList());
//        System.out.println(categories);
//        System.out.println(stringCategories);
//        System.out.println(computerGameRegisterDto.title);
//        System.out.println(computerGameRegisterDto.description);
//        System.out.println(computerGameRegisterDto.categories);
//        System.out.println(computerGameRegisterDto.languages);
//        System.out.println(computerGameRegisterDto.releaseDate);
//        System.out.println(computerGameRegisterDto.title);
//        System.out.println(computerGameRegisterDto.images.size());
        System.out.println(computerGameRegisterDto);
        System.out.println(files.length);

        service.create(cgDto, files);



        return ResponseEntity.ok(new MessageResponse("Game registered successfully!"));


    }



//    @GetMapping("/files")
//    public ResponseEntity<List<Resource>> getListFiles() {
//        List<Resource> fileInfos = imageService.loadAll();
//        System.out.println(fileInfos);
//
//
//
//        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//
//
//    }

    @GetMapping("/alltitles")
    public List<TitleListingValue> getAllTitles() {
        System.out.println("pobieranie tytułów działa");
        List<TitleListingValue> titleListingValues = service.getAllTitles();

        return titleListingValues;


    }

    @GetMapping("/allfiles")
    public List<String> getListFiles() {
        List<String> paths = imageService.loadAll();

//        System.out.println(paths);

//        List<FileInfo> files = paths.stream().map(e->{
//
//
//            byte[] file = new byte[0];
//
//
//            try {
//                file = Files.readAllBytes(e);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            String name = e.toString();
//            FileInfo fi = new FileInfo(name, file);
//
//            return fi;
//
//
//
//
//        }).collect(Collectors.toList());
//
//        System.out.println(files);





        return paths;

    }
    @GetMapping("/file/{imageName}")
    ResponseEntity<?> getImage(@PathVariable("imageName") String img) {
        FileInfo fi = imageService.load(img);
        ByteArrayResource resource = new ByteArrayResource( fi.getPicbyte());

        return ResponseEntity
                .ok()
                .contentLength(fi.getPath().toFile().length())
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);


    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCopy(@PathVariable("id") String id){
        System.out.println("W usuwaczu tytułów");

        return service.deleteComputerGame(id);
    }

//    @GetMapping("/file/{imageName}")
//    public FileInfo getImage(@PathVariable("imageName") String img) {
//        System.out.println(img);
//       return imageService.load(img);
//
//
//    }


//        @GetMapping("/files")
//    public List<FileInfo> getListFiles() {
//        List<Path> paths = imageService.loadAll();
//
//        System.out.println(paths);
//
//        List<FileInfo> files = paths.stream().map(e->{
//
//
//            byte[] file = new byte[0];
//
//
//            try {
//                file = Files.readAllBytes(e);
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//            String name = e.toString();
//            FileInfo fi = new FileInfo(name, file);
//
//            return fi;
//
//
//
//
//        }).collect(Collectors.toList());
//
//            System.out.println(files);
//
//
//
//
//
//            return files;
//
//            }

//    @GetMapping("/files")
//    public List<FileInfo> getListFiles() {
//        List<Path> paths = imageService.loadAll();
//        List<FileInfo> fileInfos = paths.stream().map(path -> {
//            String filename = path.getFileName().toString();
//            String url = MvcUriComponentsBuilder
//                    .fromMethodName(ComputerGameController.class, "getFile", path.getFileName().toString()).build().toString();
//
//            return new FileInfo(filename, url);
//        }).collect(Collectors.toList());
//
//        return fileInfos;
//    }
//
//
//    @GetMapping("/files/{filename:.+}")
//    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
//        Resource file = imageService.load(filename);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//    }
//        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
//            String filename = path.getFileName().toString();
//            String url = MvcUriComponentsBuilder
//                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
//
//            return new FileInfo(filename, url);
//        }).collect(Collectors.toList());
//
//        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
//    }

}
