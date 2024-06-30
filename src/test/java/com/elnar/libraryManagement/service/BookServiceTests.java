package com.elnar.libraryManagement.service;

import com.elnar.libraryManagement.entity.Book;
import com.elnar.libraryManagement.repository.BookRepository;
import com.elnar.libraryManagement.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class BookServiceTests {
	private static BookRepository bookRepository;
	private static BookService bookService;
	private static Book testBook;
	
	@BeforeAll
	static void setUp(){
		bookRepository = Mockito.mock(BookRepository.class);
		bookService = new BookServiceImpl(bookRepository);
		
		testBook = Book.builder()
				.id(1L)
				.title("Test title")
				.author("Test author")
				.year(2024)
				.isbn("Test isbn")
				.build();
	}
	
	@Test
	@DisplayName("Get book by id")
	void testGetBookById(){
		when(bookRepository.findById(anyLong())).thenReturn(Optional.of(testBook));
		
		Book book = bookService.getById(1L);
		
		assertNotNull(book);
		assertEquals(book.getTitle(), "Test title");
		assertEquals(book.getAuthor(), "Test author");
		assertEquals(book.getYear(), Integer.valueOf(2024));
		assertEquals(book.getIsbn(), "Test isbn");
	}
	
	@Test
	@DisplayName("Get all books")
	void testGetAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(testBook);
		
		when(bookRepository.findAll()).thenReturn(books);
		
		List<Book> result = bookService.getAll();
		
		assertNotNull(result);
		assertEquals(1, result.size());
		Book resultBook = result.get(0);
		assertEquals(resultBook.getTitle(), "Test title");
		assertEquals(resultBook.getAuthor(), "Test author");
		assertEquals(resultBook.getYear(), Integer.valueOf(2024));
		assertEquals(resultBook.getIsbn(), "Test isbn");
	}
	
	@Test
	@DisplayName("Save book")
	void testSaveBook() {
		when(bookRepository.save(any(Book.class))).thenReturn(testBook);
		
		Book book = bookService.save(testBook);
		
		assertNotNull(book);
		assertEquals("Test title", book.getTitle());
		assertEquals("Test author", book.getAuthor());
		assertEquals(Integer.valueOf(2024), book.getYear());
		assertEquals("Test isbn", book.getIsbn());
	}
	
	@Test
	@DisplayName("Delete book")
	void testDeleteBook() {
		when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
		
		bookService.deleteById(1L);
		verify(bookRepository, times(1)).delete(testBook);
	}
}
