package com.example.ELibraryManagement.controllers;

import com.example.ELibraryManagement.models.TransactionType;
import com.example.ELibraryManagement.models.User;
import com.example.ELibraryManagement.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/initiate")
    public String initiateTransaction(@RequestParam("bookId") Long bookId,
                                      @RequestParam("transactionType")TransactionType transactionType) throws Exception {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User)authentication.getPrincipal();
        Long studentId = user.getStudent().getId();

        return this.transactionService.initiate(studentId,bookId,transactionType);
    }
}
