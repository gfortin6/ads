package com.gfads.service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.gfads.dao.AuthorDao;
import com.gfads.entity.Author;

@Service
@Transactional
public class AuthorService {

	private AuthorDao authorDao;

	public AuthorService(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public List<Author> list() {
		return (List<Author>) authorDao.findAll();
	}
	
	public void add(Author author) {
		authorDao.save(author);
	}

	public boolean exists(String name) {
		return authorDao.existsByName(name);
	}
	
	public Optional<Author> findById(UUID uuid) {
		return authorDao.findById(uuid);
	}
	
	public Map<UUID,String> getListSelect(){
		Map<UUID, String> map = new LinkedHashMap<>();
		List<Author> listAuthor = list();
		Iterator<Author> iter = listAuthor.iterator();
		while (iter.hasNext()) {
			Author author = (Author) iter.next();
			map.put(author.getId(), author.getName());
		}
		return map;
		
	}
	
	

}
