package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService  transactionService;
    @PostMapping("/issue")
    public ResponseEntity issueCard(@RequestParam ("cardId") Integer cardId, @RequestParam ("bookId") Integer bookId){
        try{
            transactionService.issueCard(cardId,bookId);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
