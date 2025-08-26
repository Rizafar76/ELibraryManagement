package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
