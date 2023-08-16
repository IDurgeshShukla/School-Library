package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.Department;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public void addStudent(Student student)throws Exception {
        if(student.getRollNo()!= null){
            throw  new Exception("Duplicate_value");
        }
        studentRepository.save(student); // this is used to save something in db
    }
    public Department getDepartmentByID(Integer rollNo) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(rollNo); // it is bringing student with
        // this roll no
        if (!optionalStudent.isPresent()){
            throw new Exception("Roll no is invalid");
        }
        Student student = optionalStudent.get();
        return student.getDepartment();
    }
}
