package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNo;

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;

    private Integer numOfIssuedBooks;
    @OneToOne
    @JoinColumn
    private Student student; // this is the FK variable name

    @OneToMany(mappedBy = "libraryCard", cascade =  CascadeType.ALL)
    private List<Transaction> TransactionList = new ArrayList<>();

}
