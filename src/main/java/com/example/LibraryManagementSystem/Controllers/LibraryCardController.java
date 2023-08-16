package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Services.LibraryCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/LibraryCard")
@Slf4j
public class LibraryCardController {
    @Autowired
    LibraryCardService cardService;
    @PostMapping("/create")
    public ResponseEntity addCard(@RequestBody LibraryCard card){
        try{
            cardService.addCard(card);
            return new  ResponseEntity("card added successfully", HttpStatus.ACCEPTED);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity("Card addition failed"+ e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PutMapping("/associate-Student-to-card" ) // write only
    public ResponseEntity issueCard(@RequestParam Integer rollNo, @RequestParam Integer Id){
        try{
            cardService.associateCard(rollNo,Id);
            return new ResponseEntity("card issued successfully ", HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity("Could not issued" + e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
