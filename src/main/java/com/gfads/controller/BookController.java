package com.gfads.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.gfads.entity.Book;
import com.gfads.service.AuthorService;
import com.gfads.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	

	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute("books",bookService.list());
		return "book/list";
	}
	
	@RequestMapping("/addBook")
	public String addForm(Model model) {
		model.addAttribute("authors",authorService.getListSelect());
		model.addAttribute("book",new Book());
		return "book/add";
	}
	
	@PostMapping("/processAddBook")
	public RedirectView processAddBook(@ModelAttribute("book") Book book) {
		bookService.add(book);

	    RedirectView rv = new RedirectView("/");
	    rv.setExposeModelAttributes(false);
	    return rv;
	}	
	
	@GetMapping("/print")
	public RedirectView print(@RequestParam String id) {
		
		bookService.print(id);
		RedirectView rv = new RedirectView("/");
	    rv.setExposeModelAttributes(false);
	    return rv;
	}
	
}
