package com.example.LibraryManagementSystem.Repositories;

import com.example.LibraryManagementSystem.Models.LibraryCard;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<LibraryCard,Integer> {

}
