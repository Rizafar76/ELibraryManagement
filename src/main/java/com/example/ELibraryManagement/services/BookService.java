package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.Author;
import com.example.ELibraryManagement.models.Book;
import com.example.ELibraryManagement.repositories.BookRedisRepository;
import com.example.ELibraryManagement.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService  authorService;

    @Autowired
    BookRedisRepository bookRedisRepository;


    public Long create(Book book) {
        Author author = book.getAuthor();
        this.authorService.create(author);
        return bookRepository.save(book).getId();
    }

    public Book getById(Long id) {

        Book book = this.bookRedisRepository.get(id);

        if(book!=null){
            return book;
        }


        book = this.bookRepository.findById(id).orElse(null);
        this.bookRedisRepository.add(book);

        return this.bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        return this.bookRepository.save(book);
    }
}
