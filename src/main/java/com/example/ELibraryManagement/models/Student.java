package com.example.ELibraryManagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String mobile;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    private Integer age;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnoreProperties("student")
    private User user;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("student")
    private List<Book>books;
}
