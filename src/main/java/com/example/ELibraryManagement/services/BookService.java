package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.Author;
import com.example.ELibraryManagement.models.Book;
import com.example.ELibraryManagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService  authorService;


    public Long create(Book book) {
        Author author = book.getAuthor();
        this.authorService.create(author);
        return bookRepository.save(book).getId();
    }

    public Book getById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }
}
