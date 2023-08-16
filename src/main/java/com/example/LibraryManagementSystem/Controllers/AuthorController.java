package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.RequestDto.PenAndAuthorUpdate;
import com.example.LibraryManagementSystem.Services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
