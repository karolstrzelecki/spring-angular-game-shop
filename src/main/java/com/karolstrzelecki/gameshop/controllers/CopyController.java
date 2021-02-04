package com.karolstrzelecki.gameshop.controllers;

import com.karolstrzelecki.gameshop.daos.CgFormValues;
import com.karolstrzelecki.gameshop.daos.CopyFormValues;
import com.karolstrzelecki.gameshop.daos.SingleCopyDataDao;
import com.karolstrzelecki.gameshop.dtos.CopyAdderDto;
import com.karolstrzelecki.gameshop.models.ComputerGame;
import com.karolstrzelecki.gameshop.models.Copy.Conditions;
import com.karolstrzelecki.gameshop.models.Copy.Platform;
import com.karolstrzelecki.gameshop.models.GameCategory;
import com.karolstrzelecki.gameshop.models.Language;
import com.karolstrzelecki.gameshop.payload.response.MessageResponse;
import com.karolstrzelecki.gameshop.repository.CopyRepository;
import com.karolstrzelecki.gameshop.services.ComputerGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/copy")
public class CopyController {

    @Autowired
    ComputerGameService computerGameService;





    @GetMapping("/save")
    public CopyFormValues getValues() {
        CopyFormValues cfv = new CopyFormValues();
        List<Conditions> conditions = Arrays.asList(Conditions.values());
        List<String> stringConditions = conditions.stream().map(e -> e.description).collect(Collectors.toList());
        cfv.conditions = stringConditions;
        List<Platform> platforms = Arrays.asList(Platform.values());
        List<String> stringPlatforms = platforms.stream().map(e -> e.name).collect(Collectors.toList());
        cfv.platforms = stringPlatforms;
        System.out.println("pobieram wartości do fomrularza kopii");
        System.out.println(cfv);

        return cfv;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addCopy(@RequestBody CopyAdderDto copyAdderDto){


        System.out.println(copyAdderDto);
        computerGameService.addCopy(copyAdderDto);

        return ResponseEntity.ok(new MessageResponse("Copy added successfully!"));

    }

    @GetMapping("/getall/{id}")
    public ResponseEntity<?> getCopiesOfTitle(@PathVariable("id") String id){

        List<SingleCopyDataDao> singleCopyDataDaos = computerGameService.getAllCopies(id);

        return ResponseEntity
                .ok()
                .body(singleCopyDataDaos);

    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteCopy(@PathVariable("id") String id){


        return computerGameService.deleteCopy(id);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCopy(@RequestBody CopyAdderDto copyAdderDto){



        computerGameService.updateCopy(copyAdderDto);

        return ResponseEntity.ok(new MessageResponse("Copy added successfully!"));

    }

}
