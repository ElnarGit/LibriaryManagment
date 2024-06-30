package com.elnar.libraryManagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	@NotBlank(message = "Title is required")
	private String title;
	
	@Column(name = "author")
	@NotBlank(message = "Author is required")
	private String author;
	
	@Column(name = "year")
	@Min(value = 0, message = "Year must be a positive number")
	private Integer year;
	
	@Column(name = "isbn")
	@Size(min = 5, max = 13, message = "ISBN must be between 10 and 13 characters")
	private String isbn;
}
