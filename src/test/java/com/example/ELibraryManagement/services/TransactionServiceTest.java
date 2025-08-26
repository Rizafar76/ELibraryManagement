package com.example.ELibraryManagement.services;

import com.example.ELibraryManagement.dtos.GetStudentResponse;
import com.example.ELibraryManagement.models.*;
import com.example.ELibraryManagement.repositories.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {


    @InjectMocks
    TransactionService transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    StudentService studentService;

    @Mock
    BookService bookService;

    @Test
    public void testCalculateFine(){

        Book book = Book.builder()
                .id(1L)
                .name("Maths 101")
                .build();

        Student student = Student.builder()
                        .id(1L)
                        .name("Posty")
                        .build();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .status(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1750322300000L))
                .build();

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndStatusOrderByIdDesc(
Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService, "maxDaysAllowed", 15);
        ReflectionTestUtils.setField(transactionService, "finePerDay", 1);

        int fine = transactionService.getFine(book,student);

        Assert.assertEquals(fine,15);

    }

    @Test
    public void testReturnBook() throws Exception {



        Student student = Student.builder()
                .id(1L)
                .name("Posty")
                .build();

        Book book = Book.builder()
                .id(1L)
                .name("Maths 101")
                .student(student)
                .build();



        GetStudentResponse getStudentResponse = GetStudentResponse.builder()
                .student(student)
                .build();

        String externalId = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .id(1L)
                .externalId(externalId)
                .book(book)
                .student(student)
                .transactionType(TransactionType.ISSUANCE)
                .status(TransactionStatus.SUCCESS)
                .updatedOn(new Date(1752916555000L))
                .build();

        Mockito.when(bookService.getById(1L)).thenReturn(book);
        Mockito.when(studentService.getStudent(1L)).thenReturn(getStudentResponse);


        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

        Mockito.when(transactionRepository.findTopByBookAndStudentAndTransactionTypeAndStatusOrderByIdDesc(
                Mockito.eq(book),Mockito.eq(student),Mockito.eq(TransactionType.ISSUANCE),Mockito.eq(TransactionStatus.SUCCESS)
        )).thenReturn(transaction);

        ReflectionTestUtils.setField(transactionService, "maxDaysAllowed", 15);
        ReflectionTestUtils.setField(transactionService, "finePerDay", 1);

        Mockito.when(bookService.saveBook(Mockito.any(Book.class))).thenReturn(book);


        String txnId = transactionService.initiateReturn(1L,1L);

        Assert.assertEquals(txnId,externalId);


    }
}
