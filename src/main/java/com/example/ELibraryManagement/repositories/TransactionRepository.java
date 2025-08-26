package com.example.ELibraryManagement.repositories;

import com.example.ELibraryManagement.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findTopByBookAndStudentAndTransactionTypeAndStatusOrderByIdDesc(Book book, Student student, TransactionType transactionType, TransactionStatus transactionStatus);
}
