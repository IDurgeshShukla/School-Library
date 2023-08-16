package com.example.LibraryManagementSystem.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PenAndAuthorUpdate {
    private String newPen;
    private  Integer authorId;
    private String newName;

}
