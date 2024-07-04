package com.example.lawrecontest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.lawrecontest.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    Page<Book> findAll(Pageable pageable);

    @Query(value = "SELECT DISTINCT b.genre FROM books b", nativeQuery = true)
    List<String> lovGenre();
}