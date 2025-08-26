package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
