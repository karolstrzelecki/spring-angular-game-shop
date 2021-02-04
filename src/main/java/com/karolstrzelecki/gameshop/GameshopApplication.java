package com.karolstrzelecki.gameshop;

import com.karolstrzelecki.gameshop.services.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class GameshopApplication implements CommandLineRunner {
    @Resource
    ImageService imageService;

    public static void main(String[] args) {
        SpringApplication.run(GameshopApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
//       imageService.init();
    }

}
