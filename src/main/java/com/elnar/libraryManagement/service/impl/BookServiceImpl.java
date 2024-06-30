package com.elnar.libraryManagement.service.impl;

import com.elnar.libraryManagement.entity.Book;
import com.elnar.libraryManagement.exception.BookNotFoundException;
import com.elnar.libraryManagement.repository.BookRepository;
import com.elnar.libraryManagement.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;
	
	@Override
	public Book getById(Long id) {
		log.info("IN BookServiceImpl getById {}", id);
		return bookRepository.findById(id).orElseThrow(() ->
				new BookNotFoundException("Book with id: " + id + " not found"));
	}
	
	@Override
	public List<Book> getByTitle(String title) {
		log.info("IN BookServiceImpl getByTitle {}", title);
		return bookRepository.findByTitle(title);
	}
	
	@Override
	public List<Book> getByAuthor(String author) {
		log.info("IN BookServiceImpl getByAuthor {}", author);
		return bookRepository.findByAuthor(author);
	}
	
	@Override
	public List<Book> getByYear(Integer year) {
		log.info("IN BookServiceImpl getByYear {}", year);
		return bookRepository.findByYear(year);
	}
	
	@Override
	public List<Book> getAll() {
		log.info("IN BookServiceImpl getAll");
		return bookRepository.findAll();
	}
	
	@Override
	@Transactional
	public Book save(Book book) {
		log.info("IN BookServiceImpl save {}", book);
		return bookRepository.save(book);
	}
	
	@Override
	@Transactional
	public Book update(Long id, Book book) {
		log.info("IN BookServiceImpl update {} with id {}", book, id);
		
		Book updatedBook = getById(id);
		
		updatedBook.setTitle(book.getTitle());
		updatedBook.setAuthor(book.getAuthor());
		updatedBook.setYear(book.getYear());
		updatedBook.setIsbn(book.getIsbn());
		
		return bookRepository.save(updatedBook);
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		log.info("IN BookServiceImpl deleteById {}", id);
		Book book = getById(id);
		bookRepository.delete(book);
	}
}
