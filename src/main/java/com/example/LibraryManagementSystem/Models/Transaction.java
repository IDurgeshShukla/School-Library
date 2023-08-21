package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;
    @Enumerated(value =  EnumType.STRING)
    private TransactionType transactionType;
    private Integer fineAmount;
    //mapping
    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private LibraryCard libraryCard;

    public Transaction(TransactionStatus transactionStatus, TransactionType transactionType, Integer fineAmount) {
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.fineAmount = fineAmount;
    }
}
