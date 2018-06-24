package com.gfads.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.gfads.entity.Author;

public interface AuthorDao extends CrudRepository<Author, UUID>{

	boolean existsByName(String name);
	
}
