package com.example.LibraryManagementSystem.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private Integer authorId;
    private String authorName;
    private Integer noOfBooks;
    private Integer age;
}
