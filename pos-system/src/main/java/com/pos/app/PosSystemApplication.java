package com.pos.app;

import com.pos.app.service.FileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class PosSystemApplication implements CommandLineRunner {
	@Resource
	FileStorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(PosSystemApplication.class, args);
	}
	@Override
	public void run(String... arg) throws Exception {
//    storageService.deleteAll();
		storageService.init();
	}


}
