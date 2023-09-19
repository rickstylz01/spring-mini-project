package com.example.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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
    private Set<Book> books = new HashSet<>();
}
