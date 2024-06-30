package com.elnar.libraryManagement.rest;

import com.elnar.libraryManagement.entity.Book;
import com.elnar.libraryManagement.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookRestControllerV1 {
	private final BookService bookService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id){
		Book book = bookService.getById(id);
		return ResponseEntity.ok().body(book);
	}
	
	@GetMapping("/title")
	public List<Book> getBookByTitle(@RequestParam String title){
		return bookService.getByTitle(title);
	}
	
	@GetMapping("/author")
	public List<Book> getBookByAuthor(@RequestParam String author){
		return bookService.getByAuthor(author);
	}
	
	@GetMapping("/year")
	public List<Book> getBookByYear(@RequestParam Integer year){
		return bookService.getByYear(year);
	}
	
	@GetMapping()
	public List<Book> getAllBooks(){
		return bookService.getAll();
	}
	
	@PostMapping()
	public ResponseEntity<Book> saveBook(@RequestBody @Valid Book book){
		Book saveBook = bookService.save(book);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saveBook);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody @Valid Book book){
		Book updatedBook = bookService.update(id, book);
		return ResponseEntity.ok().body(updatedBook);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id){
		bookService.deleteById(id);
		return ResponseEntity.ok().body("Book with id " + id + " deleted successfully");
	}
}
