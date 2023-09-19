package com.example.library.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Genre {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int popularityRating;

    @Column
    private LocalDate creationDate;

    @Column
    private int booksCount; // number of books within this genre

    @ManyToMany(mappedBy = "genres")
    @JoinColumn(name = "user_id")
    private Set<Book> books = new HashSet<>();
}
