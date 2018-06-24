package com.gfads.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.gfads.entity.Book;

public interface BookDao extends CrudRepository<Book, UUID> {
}
