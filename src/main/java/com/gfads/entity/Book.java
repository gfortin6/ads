package com.gfads.entity;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Book {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;

	@ManyToOne(cascade = CascadeType.ALL)
	private Author author;

	@Column(length = 120)
	@NotEmpty
	private String name;

	@Column(length = 20, name = "isbn_code")
	@NotEmpty
	private String isbnCode;
	

	public Book() {
	}

	public Book(Author author, String name, String isbnCode) {
		super();
		this.author = author;
		this.name = name;
		this.isbnCode = isbnCode;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", name=" + name + ", isbnCode=" + isbnCode + "]";
	}

}
