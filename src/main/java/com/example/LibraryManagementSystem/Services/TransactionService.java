package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Enums.TransactionType;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Transaction;
import com.example.LibraryManagementSystem.Repositories.BookRepo;
import com.example.LibraryManagementSystem.Repositories.CardRepo;
import com.example.LibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepo bookRepo;
    @Autowired
    CardRepo cardRepo;
    public final int maxLimit =6;
    public void issueCard(Integer cardId, Integer bookId) throws Exception{
        Transaction transaction = new Transaction(TransactionStatus.Pending,TransactionType.Issued,0);
        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (!bookOptional.isPresent()) throw new Exception("book is not present.");
        Optional<LibraryCard> libraryCardOptional = cardRepo.findById(cardId);
    }

}
