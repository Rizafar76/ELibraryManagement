package com.example.ELibraryManagement.dtos;


import com.example.ELibraryManagement.models.Book;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBookResponse {
    Book book;
}
