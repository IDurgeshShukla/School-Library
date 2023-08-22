package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repositories.AuthorRepository;
import com.example.LibraryManagementSystem.RequestDto.PenAndAuthorUpdate;
import com.example.LibraryManagementSystem.ResponseDto.AuthorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(Author author)throws  Exception {
        if (author.getAuthorId() == null){
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

    public List<String> findBooksByAuthorId(Integer authorId) throws  Exception{
        List<String > books = new ArrayList<>();
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (!authorOptional.isPresent()) throw new Exception("author doesn't exist in our system");
        Author author = authorOptional.get();
        List<Book> bookList = author.getBookList();
        for (Book book :
                bookList) {
            books.add(book.getTitle());
        }
        return books;
    }

    public AuthorResponseDto findAuthorByAuthorId(Integer authorId) throws Exception{
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (!optionalAuthor.isPresent()) throw new Exception("Author not Found");
        Author author = optionalAuthor.get();
        AuthorResponseDto authorResponseDto = new AuthorResponseDto(author.getAuthorId(),author.getAuthorName(),
                author.getBookList().size(), author.getAge());
        return authorResponseDto;
    }

    public void deleteAuthorById(Integer authorId)throws  Exception{
        try{
            authorRepository.deleteById(authorId);
        }catch (Exception e) {
            throw new Exception("Invalid Id");
        }
    }
}
