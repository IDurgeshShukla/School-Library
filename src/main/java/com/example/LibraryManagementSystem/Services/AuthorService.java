package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import com.example.LibraryManagementSystem.RequestDto.PenAndAuthorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(Author author)throws  Exception {
        if (author.getAuthorId() != null){
            authorRepository.save(author);
        } else throw new Exception("Id is not allowed");
    }
    public void updatePenAndAuthor(PenAndAuthorUpdate penAndAuthor) throws Exception {
        Optional<Author> authorOptional = authorRepository.findById(penAndAuthor.getAuthorId());
        if (!authorOptional.isPresent()){
            throw new Exception("Author is not present.");
        }
        Author author = authorOptional.get();
        author.setPenName(penAndAuthor.getNewPen());
        author.setAuthorName(penAndAuthor.getNewName());
        //update in repos
        authorRepository.save(author);
    }
}
