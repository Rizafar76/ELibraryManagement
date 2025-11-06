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
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Student student;

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transaction> transactions;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;


}
