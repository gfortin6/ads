package com.gfads.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfads.dao.BookDao;
import com.gfads.entity.Book;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookDao bookDao;

	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> list() {
		return (List<Book>) bookDao.findAll();
	}
	
	public void add(Book book) {
		bookDao.save(book);
	}
	
	public Optional<Book> findById(UUID uuid) {
		return bookDao.findById(uuid);
	}
	
	public void delete(UUID id) {
		bookDao.deleteById(id);
	}

}
