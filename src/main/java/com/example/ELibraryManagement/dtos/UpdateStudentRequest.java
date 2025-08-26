package com.example.ELibraryManagement.dtos;

import com.example.ELibraryManagement.models.Gender;
import com.example.ELibraryManagement.models.Student;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentRequest {

    private String name;

    private Gender gender;

    private String mobile;

    private Integer age;

    public Student toStudent(){
        return Student.builder()
                .name(name)
                .gender(gender)
                .mobile(mobile)
                .age(age)
                .build();
    }


}
