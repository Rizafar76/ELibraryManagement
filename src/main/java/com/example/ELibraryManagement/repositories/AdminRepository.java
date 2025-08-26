package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Long> {
}
