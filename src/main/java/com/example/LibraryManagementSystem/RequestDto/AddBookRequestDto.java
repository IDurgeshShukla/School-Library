package com.example.LibraryManagementSystem.RequestDto;

import com.example.LibraryManagementSystem.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddBookRequestDto {
    private String title;
    private Boolean isAvailable;
    private Genre genre;
    private LocalDate localDate ;
    private Integer price;
    private Integer authorId;

}
