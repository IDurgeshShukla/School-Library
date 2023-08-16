package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer bookId;
    @Column(unique = true)
    private String title;
    private Boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private LocalDate localDate ;
    private Integer price;

    @ManyToOne
    @JoinColumn
            // unidirectional mapping
    private Author author;

    public Book(String title, Boolean isAvailable, Genre genre, LocalDate localDate, Integer price, Author author) {
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.localDate = localDate;
        this.price = price;
        this.author = author;
    }
}
