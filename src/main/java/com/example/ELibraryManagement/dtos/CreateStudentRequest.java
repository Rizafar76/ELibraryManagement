package com.example.ELibraryManagement.dtos;

import com.example.ELibraryManagement.models.Gender;
import com.example.ELibraryManagement.models.Student;
import com.example.ELibraryManagement.models.StudentStatus;
import com.example.ELibraryManagement.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String name;

    private Gender gender;

    @NotBlank
    private String mobile;

    private Integer age;

    public Student toStudent() {
        return Student
                .builder()
                .name(name)
                .gender(gender)
                .mobile(mobile)
                .age(age)
                .status(StudentStatus.ACTIVE)
                .user(User.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
