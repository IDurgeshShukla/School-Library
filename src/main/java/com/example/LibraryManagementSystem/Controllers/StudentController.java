package com.example.LibraryManagementSystem.Controllers;

import com.example.LibraryManagementSystem.Enums.Department;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j // used for logging
@RestController // used to tell system it is controller class

@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        try {
            studentService.addStudent(student);
        } catch (Exception e){
            log.error("student admission failed" +e.getMessage());
            return new ResponseEntity<>("Error caught while adding student Reason = " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Student added successfully", HttpStatus.CREATED);
    }
    @GetMapping("/get-department-by-id/{rollNo}")
    public ResponseEntity getDepartmenntByID(@PathVariable Integer  rollNo){
        try {
            Department department = studentService.getDepartmentByID(rollNo);
            return new ResponseEntity(department,HttpStatus.FOUND);
        } catch (Exception e){
            log.error("department not found" + e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
