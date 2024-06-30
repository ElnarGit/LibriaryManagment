package com.elnar.libraryManagement.service;

import com.elnar.libraryManagement.entity.Book;

import java.util.List;

public interface BookService {
	 Book getById(Long id);
	 
	 List<Book> getByTitle(String title);
	 
	 List<Book> getByAuthor(String author);
	 
	 List<Book> getByYear(Integer year);
	 
	 List<Book> getAll();
	 
	 Book save(Book book);
	 
	 Book update(Long id, Book book);
	 
	 void deleteById(Long id);
}
