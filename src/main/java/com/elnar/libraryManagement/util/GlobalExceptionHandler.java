package com.elnar.libraryManagement.util;

import com.elnar.libraryManagement.exception.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<AppError> catchBookNotFoundException(BookNotFoundException e){
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),
				e.getMessage()),
				HttpStatus.NOT_FOUND);
	}
}
