package com.example.LibraryManagementSystem.Repositories;

import com.example.LibraryManagementSystem.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
