package com.example.LibraryManagementSystem.Models;

import com.example.LibraryManagementSystem.Enums.Department;
import com.example.LibraryManagementSystem.Enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id // it is primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // used for generating value
    private Integer rollNo;
    private String name;
    private Integer age;
    @Enumerated(value = EnumType.STRING)  //used to consider custom datatype only string type
     private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Department department;
    @Column(unique = true)
    private String emailId;

    //below is implementation of bie directional mapping
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    //mappingType     FK in child table
    // it's mean that if there is changes done in parent
    // (student) table update all the changes to it's child(LibraryCard table) that is written below
    private LibraryCard libraryCard;
}
