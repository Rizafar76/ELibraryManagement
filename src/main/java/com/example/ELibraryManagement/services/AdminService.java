package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.dtos.GetStudentResponse;
import com.example.ELibraryManagement.models.Admin;
import com.example.ELibraryManagement.models.Authority;
import com.example.ELibraryManagement.models.Student;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.repositories.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    private ObjectMapper mapper;

    public Long createAdmin(Admin admin) {

        User user = this.userService.createUser(admin.getUser(), Authority.ADMIN);
        admin.setUser(user);
        return this.adminRepository.save(admin).getId();
    }

}
