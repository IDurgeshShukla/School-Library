package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Enums.Department;
import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repositories.StudentRepository;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String getCardStatusOfGivenStudentId(Integer studentId) throws Exception {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (!studentOptional.isPresent()) throw new Exception(" Student not found");
        Student student = studentOptional.get();
        return student.getLibraryCard().getCardStatus().toString();
    }

    public int countStudentsWithDepartment(String department) throws  Exception{
        try {
            List<Student> students = studentRepository.findStudentsWithDepartment(department);
            return students.size();
        } catch (Exception e){
            throw new Exception("Not found any student with given department");
        }
    }
}
