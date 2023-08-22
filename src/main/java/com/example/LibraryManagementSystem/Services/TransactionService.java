package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.CardStatus;
import com.example.LibraryManagementSystem.Enums.TransactionStatus;
import com.example.LibraryManagementSystem.Enums.TransactionType;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Transaction;
import com.example.LibraryManagementSystem.Repositories.BookRepo;
import com.example.LibraryManagementSystem.Repositories.CardRepo;
import com.example.LibraryManagementSystem.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
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
        if (!bookOptional.isPresent()) throw new Exception("book id is unavailable");
        Book book = bookOptional.get();
        if (!book.getIsAvailable()) throw new Exception("book is unavailable");

        Optional<LibraryCard> libraryCardOptional = cardRepo.findById(cardId);
        if (!libraryCardOptional.isPresent()) throw new Exception("card Id is invalid");
        LibraryCard card = libraryCardOptional.get();
        if(!card.getCardStatus().equals(CardStatus.Active)) { // save failed transaction in db
            transaction.setTransactionStatus(TransactionStatus.Failed);
            transaction = transactionRepository.save(transaction);
            throw new Exception("Card is not active");
        }
        if (card.getNumOfIssuedBooks() >= maxLimit) {
            transaction.setTransactionStatus(TransactionStatus.Failed);
            transaction = transactionRepository.save(transaction);
            throw new Exception("max book limit reached");
        }
        transaction.setTransactionStatus(TransactionStatus.Success);
        //update book availability
        book.setIsAvailable(false);
        card.setNumOfIssuedBooks(card.getNumOfIssuedBooks()+1);
        Transaction transactionWithId = transactionRepository.save(transaction);
        book.getTransactionList().add(transactionWithId);
        card.getTransactionList().add(transactionWithId);

        // save both in book repo and card repo
        bookRepo.save(book);
        transactionRepository.save(transaction);

    }

    public void returnBook(Integer bookId, Integer cardId)throws  Exception {
        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (!bookOptional.isPresent()) throw new Exception("Invalid book Id");
        Book book = bookOptional.get();
        if (book.getIsAvailable()) throw new Exception("book is not issued");
        Optional<LibraryCard> cardOptional = cardRepo.findById(cardId);
        if(!cardOptional.isPresent()) throw new Exception("Invalid cardId");
        LibraryCard card = cardOptional.get();
        List<Transaction> transactions =
                transactionRepository.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(book
                        ,card,TransactionStatus.Success,TransactionType.Issued);

        Transaction latestTransaction = transactions.get(transactions.size() - 1);

        // line no 77 to 79 is used to find date and convert to days
        Date issueDate = latestTransaction.getCreatedAt();
        long millisecond = Math.abs(System.currentTimeMillis() - issueDate.getTime());
        long numOfDaysIssuedBook = TimeUnit.DAYS.convert(millisecond,TimeUnit.MILLISECONDS);
        int fineAmount = 0;
        if (numOfDaysIssuedBook > 15) {
            fineAmount = (int)(numOfDaysIssuedBook - 15)*5;
        }
        // update availability of book
        book.setIsAvailable(true);
        // update no of books issue on card
        card.setNumOfIssuedBooks(card.getNumOfIssuedBooks() - 1);
        // create new transaction
        Transaction transaction = new Transaction(TransactionStatus.Success, TransactionType.Returned,fineAmount);
        //update transaction in card repo and bookrepo
        card.getTransactionList().add(transaction);
        book.getTransactionList().add(transaction);

        //save the parents
        bookRepo.save(book);
        transactionRepository.save(transaction);
        cardRepo.save(card);
    }
}
