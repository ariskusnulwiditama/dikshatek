package com.example.lawrecontest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import com.example.lawrecontest.entity.Book;
import com.example.lawrecontest.repository.BookRepository;
import com.example.lawrecontest.service.response.CustomNotFoundException;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    public Page<Book> getAllBooks(Pageable pageable) {
        try {
            return bookRepository.findAll(pageable);
        } catch (NotFound e) {
            throw new CustomNotFoundException(404, "Not Found", "Books not found");
        } 
        
    }

    public List<Book> getBooksByAuthor(String author) {
    return bookRepository.findAll().stream()
        .filter(book -> book.getAuthor().equalsIgnoreCase(author))
        .collect(Collectors.toList());
    }

    public List<String> getLovGenre() {
        return bookRepository.lovGenre();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new CustomNotFoundException(404, "Not Found", "Book not found");
        }
    }

    public void deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Book updateBook(Long id, Book book) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            throw new CustomNotFoundException(404, "Not Found", "Book not found");
        }
       
        bookOptional.get().setTitle(book.getTitle());
        bookOptional.get().setAuthor(book.getAuthor());
        bookOptional.get().setPrice(book.getPrice());
        bookRepository.save(bookOptional.get());
        
        return bookOptional.get();
    }

    public void saveBook(Book book) {
        try {
            bookRepository.save(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}