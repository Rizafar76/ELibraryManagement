package com.example.ELibraryManagement.dtos;

import com.example.ELibraryManagement.models.Author;
import com.example.ELibraryManagement.models.Book;
import com.example.ELibraryManagement.models.Genre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    @NotBlank
    private String name;

    private Genre genre;

    private String authorName;

    @Email
    @NotBlank
    private String email;

    public Book toBook(){
        return Book
                .builder()
                .name(name)
                .genre(genre)
                .author(Author
                        .builder()
                        .name(authorName)
                        .email(email)
                        .build())
                .build();
    }


}
