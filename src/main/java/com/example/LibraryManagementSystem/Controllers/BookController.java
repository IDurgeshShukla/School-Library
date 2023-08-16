package com.example.LibraryManagementSystem.Controllers;
import com.example.LibraryManagementSystem.RequestDto.AddBookRequestDto;
import com.example.LibraryManagementSystem.Services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDto book){
        try {
            bookService.addBook(book);
            return new ResponseEntity("book added Successfully ", HttpStatus.ACCEPTED);
        } catch (Exception e){
            log.error("Unbale to add book" +e.getMessage());
            return new ResponseEntity("addition book is failed" + e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
