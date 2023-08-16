package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.LibraryCard;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositories.CardRepo;
import com.example.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryCardService {
    @Autowired
    CardRepo cardRepo;
    @Autowired
    StudentRepository studentRepo;
    public void addCard(LibraryCard card) {
        cardRepo.save(card);
    }
    public void associateCard(Integer studentId, Integer cardId) throws Exception {
        if(!cardRepo.existsById(cardId)){
            throw new Exception("card doesn't exist in DB");
        }
        if(!studentRepo.existsById(studentId)){
            throw new Exception("student is not present in our db");
        }

        Optional<Student> studentObj = studentRepo.findById(studentId);
        Optional<LibraryCard> cardObj = cardRepo.findById(cardId);

        Student student = studentObj.get();
        LibraryCard card = cardObj.get();
        //associate both of them
        student.setLibraryCard(card);
        card.setStudent(student);
    }
}
