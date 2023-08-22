package com.example.LibraryManagementSystem.Repositories;

import com.example.LibraryManagementSystem.Enums.Department;
import com.example.LibraryManagementSystem.Models.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "select * from student where department = ?1",nativeQuery = true)
    List<Student> findStudentsWithDepartment(String department);
}
