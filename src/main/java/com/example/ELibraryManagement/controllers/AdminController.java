package com.example.ELibraryManagement.controllers;

import com.example.ELibraryManagement.dtos.CreateAdminRequest;
import com.example.ELibraryManagement.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/add") // /user/signup
    public Long addAdmin(@RequestBody CreateAdminRequest createAdminRequest) {
        return this.adminService.createAdmin(createAdminRequest.toAdmin());
    }
}
