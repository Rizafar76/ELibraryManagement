package com.example.ELibraryManagement.controllers;

import com.example.ELibraryManagement.dtos.CreateBookRequest;
//import com.example.batch3minorproject.dtos.CreateBookResponse;
import com.example.ELibraryManagement.models.Book;
import com.example.ELibraryManagement.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService  bookService;

    @PostMapping("/add")
    public Long createBook(@Valid @RequestBody CreateBookRequest createBookRequest){

        return this.bookService.create(createBookRequest.toBook());
    }

    @GetMapping("get/{id}")
    public Book getBook(@PathVariable Long id){
        return this.bookService.getById(id);
    }
}
