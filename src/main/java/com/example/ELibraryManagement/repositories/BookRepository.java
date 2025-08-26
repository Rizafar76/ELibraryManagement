package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {

}
