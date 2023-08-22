package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService  transactionService;
    @PostMapping("/issue-book")
    public ResponseEntity<String> issueCard(@RequestParam ("cardId") Integer cardId, @RequestParam ("bookId") Integer bookId){
        try{
            transactionService.issueCard(cardId,bookId);
            return new ResponseEntity<>("card issued successfully" , HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }
    @GetMapping("/return-book")
    public ResponseEntity returnBook(@RequestParam Integer bookId, @RequestParam Integer cardId){
        try {
            transactionService.returnBook(bookId,cardId);
            return new ResponseEntity("book returned successfully", HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Failed to return book" + e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
