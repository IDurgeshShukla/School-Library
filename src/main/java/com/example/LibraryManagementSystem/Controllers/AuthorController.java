package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.RequestDto.PenAndAuthorUpdate;
import com.example.LibraryManagementSystem.ResponseDto.AuthorResponseDto;
import com.example.LibraryManagementSystem.Services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){
        try {
            authorService.addAuthor(author);
            return new ResponseEntity<>("author added successfully", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Author could not be added " + e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }

    }
    @PutMapping("/update-pen-and-name")
    public ResponseEntity updatePenAndName(@RequestBody PenAndAuthorUpdate penAndAuthorUpdate){
        try {
            authorService.updatePenAndAuthor(penAndAuthorUpdate);
            return new ResponseEntity("changes done", HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Error occured"+ e.getMessage());
            return new ResponseEntity("Couldn't updated "+ e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @GetMapping("/find-books(title)-with-author-id")
    public ResponseEntity findBooksByAuthorId(@RequestParam Integer authorId){
        try{
            List<String > books = authorService.findBooksByAuthorId(authorId);
            return new ResponseEntity(books, HttpStatus.FOUND);

        }catch (Exception e){
            log.error("Error in finding books" + e.getMessage());
            return new ResponseEntity("Couldn't find books with this given Id" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/find-author-by-authorId")
    public ResponseEntity findAuthorByAuthorId(@RequestParam ("authorId") Integer authorId){
        try {
            AuthorResponseDto authorResponseDto = authorService.findAuthorByAuthorId(authorId);
            return new ResponseEntity(authorResponseDto, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity("Author not found", HttpStatus.NOT_FOUND);
        }
    }
}
