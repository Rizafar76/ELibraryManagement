package com.example.ELibraryManagement.dtos;

import com.example.ELibraryManagement.models.Student;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStudentResponse {

    Student student;
}
