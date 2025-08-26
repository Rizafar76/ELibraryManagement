package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.Author;
import com.example.ELibraryManagement.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author create(Author author) {
        return authorRepository.save(author);
    }
}
