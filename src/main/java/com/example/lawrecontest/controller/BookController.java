package com.example.lawrecontest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.lawrecontest.entity.Book;
import com.example.lawrecontest.service.BookService;
import com.example.lawrecontest.service.helper.PaginationUtil;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<GeneralBodyResponseV2<Object>> getAllBooks(@RequestParam MultiValueMap<String, String> queryParam, 
    Pageable pageable, UriComponentsBuilder uriComponentsBuilder) {
        Page<Book> page = bookService.getAllBooks(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriComponentsBuilder.queryParams(queryParam), page);
        return ResponseEntity.ok()
        .headers(headers)
        .body(new GeneralBodyResponseV2<Object>(
            200, 
            "Success", 
            "success",
            pageable.getPageNumber(),
                        page.getSize(),
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page.getContent()

            ));
    }

    @GetMapping("/lov-genre")
    public ResponseEntity<GeneralBodyResponse> getLovGenre() {
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", bookService.getLovGenre()));
    }

    @GetMapping("/author")
    public ResponseEntity<GeneralBodyResponse> getBooksByAuthor(@RequestParam String author) {
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", bookService.getBooksByAuthor(author)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralBodyResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", bookService.getBookById(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralBodyResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GeneralBodyResponse> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book result =  bookService.updateBook(id, book);
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", result));
    }

    @PostMapping("/create")
    public ResponseEntity<GeneralBodyResponse> createBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok().body(new GeneralBodyResponse(200, "Success", "success", null));
    }

}