package com.gfads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gfads.entity.Author;
import com.gfads.service.AuthorService;

//@Component
public class DataLoader implements ApplicationRunner {

	private AuthorService authorService;

	@Autowired
	public DataLoader(AuthorService authorService) {
		this.authorService = authorService;
	}

	public void run(ApplicationArguments args) {
		List<Author> list = new ArrayList<Author>();
		list.add(new Author("Dan Brown"));
		list.add(new Author("J.K. Rowling"));
		list.add(new Author("J.R.R Tolkien"));
		list.add(new Author("Stephen King"));
		Iterator<Author> iter = list.iterator();
		while (iter.hasNext()) {
			Author author = (Author) iter.next();
			if (!authorService.exists(author.getName())) {
				authorService.add(author);
			}
		}
	}
}