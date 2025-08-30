package com.example.ELibraryManagement;

import com.example.ELibraryManagement.services.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class ELibraryManagement {

    public static void main(String[] args) {
        SpringApplication.run(ELibraryManagement.class, args);
    }

}
