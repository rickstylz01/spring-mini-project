package com.example.library.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
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
    private Boolean availability;

    @Column
    private int availableCopies;

    @Column
    private int borrowedCopies;
}
