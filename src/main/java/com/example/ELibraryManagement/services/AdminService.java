package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.models.Admin;
import com.example.ELibraryManagement.models.Authority;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    AdminRepository adminRepository;

    public Long createAdmin(Admin admin) {

        User user = this.userService.createUser(admin.getUser(), Authority.ADMIN);
        admin.setUser(user);
        return this.adminRepository.save(admin).getId();
    }
}
