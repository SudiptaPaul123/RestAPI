package com.example.RestAPI.BookService;

import java.text.Collator;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.RestAPI.Entity.Book;

@Service
public class BookService {
	
	private static ArrayList<Book> bookList=new ArrayList();
	
	static {
		bookList.add(new Book(1,"Royal Story","Ankur Bhawal"));
		bookList.add(new Book(2,"Kakababu","Raju Gupta"));
		bookList.add(new Book(3,"Quantative Aptitude","RS Agarwal"));
	}
	
	public ArrayList<Book> getBooks()
	{
		return bookList;
	}
	
	public Book getBookById(int id)
	{
		try {
			return bookList.stream().filter(n->n.getId()==id).findFirst().get();			
		} catch(Exception e)
		{
			return null;
		}
		
	}
	
	public Book addBooks(Book b)
	{
		if(bookList.add(b))
		{
			return b;
		} else {
			return null;
		}
	}
	
	public void updateBook(Book b,int id)
	{
		bookList =bookList.stream().map(n->{
			if(n.getId()==id)
			{
				n.setBook(b.getBook());
				n.setAuthor(b.getAuthor());
			}
			return n;			
		}).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void deleteBook(int id)
	{
		bookList =bookList.stream().filter(n->n.getId()!=id).collect(Collectors.toCollection(ArrayList::new));		
	}

}
