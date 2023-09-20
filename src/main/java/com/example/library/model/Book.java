package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String author;

    @Column
    private LocalDate publicationDate;

    @Column
    private String publisher;

    @Column
    private Float price;

    @Column
    private Integer numberOfCopies;

    @ManyToMany(mappedBy = "books")
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne
    private User user;
}
