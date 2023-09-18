package com.example.library.model;

import lombok.Data;

import javax.persistence.*;

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
}
