package com.gfads.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gfads.dao.BookDao;
import com.gfads.entity.Book;

@Service
@Transactional
public class BookService {
	
	private final BookDao bookDao;

	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> list() {
		return (List<Book>) bookDao.findAll();
	}
	
	public void add(Book book) {
		bookDao.save(book);
	}
	
	public void print(String id) {
		Optional<Book> book = findById(UUID.fromString(id));
		System.out.println("P{RINT");
	}
	
	public Optional<Book> findById(UUID uuid) {
		return bookDao.findById(uuid);
	}

}
