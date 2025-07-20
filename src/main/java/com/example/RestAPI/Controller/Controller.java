package com.example.RestAPI.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestAPI.BookService.BookService;
import com.example.RestAPI.Entity.Book;

@RestController
public class Controller {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("books") 
	public ResponseEntity<ArrayList<Book>> getBook() 
	{ 
		ArrayList<Book> books=bookService.getBooks();
		if(books.size()<=0)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(books);
		}
	}
	
	@GetMapping("books/{id}") 
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) 
	{ 
		Book book=bookService.getBookById(id);
		if(book==null)
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(book);
		} 
	}
	 
	
	@PostMapping("addBook")
	public ResponseEntity<Book> addBook(@RequestBody Book b)
	{	
		Book newBook=null;
		try {		
			newBook=bookService.addBooks(b);
			return ResponseEntity.status(HttpStatus.CREATED).body(newBook);						
		} catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}				
		
	}
	
	@PutMapping("books/{id}") 
	public ResponseEntity<Book> updateBook(@RequestBody Book b,@PathVariable("id") int id)
	{				
		try {		
			bookService.updateBook(b,id);
			return ResponseEntity.status(HttpStatus.OK).body(b);						
		} catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
	}
	
	@DeleteMapping("books/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable("id") int id)
	{
		try {		
			bookService.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book deleted");						
		} catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error deleting book: " + e.getMessage());
		}	

	}

}
