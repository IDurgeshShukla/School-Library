package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import com.example.LibraryManagementSystem.Repositories.BookRepo;
import com.example.LibraryManagementSystem.RequestDto.AddBookRequestDto;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BookService {
@Autowired
    BookRepo bookRepo;
@Autowired
AuthorRepository authorRepository;
    public void addBook(AddBookRequestDto bookAuthor) throws  Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(bookAuthor.getAuthorId());

        if(!optionalAuthor.isPresent()){
            throw new Exception("Author is not present.");
        }
        // create object of book from the
        Author author = optionalAuthor.get();
        Book book = new Book(bookAuthor.getTitle(),bookAuthor.getIsAvailable(),bookAuthor.getGenre(),
                bookAuthor.getLocalDate(),bookAuthor.getPrice(),author);

        // to maintain bie directional mapping we need to update/set entities in both the tables
        book.setAuthor(author);
        List<Book> list = author.getBookList();
        list.add(book);
        author.setBookList(list);
    }
}
