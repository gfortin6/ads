package com.gfads.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfads.entity.Book;
import com.gfads.service.AuthorService;
import com.gfads.service.BookService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping("/")
	public String list(Model model) {
		model.addAttribute("books", bookService.list());
		return "book/list";
	}

	@RequestMapping("/addBook")
	public String addForm(Model model) {
		model.addAttribute("authors", authorService.getListSelect());
		model.addAttribute("book", new Book());
		return "book/add";
	}

	@PostMapping("/processAddBook")
	public String processAddBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
		String result = "redirect:/";
		if (!bindingResult.hasErrors()) {
			bookService.add(book);
		} else {
			model.addAttribute("authors", authorService.getListSelect());
			result = "book/add";
		}
		return result;
	}

	@GetMapping(value = "/deleteBook")
	public String delete(@RequestParam String id) {
		bookService.delete(UUID.fromString(id));
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	@ResponseBody
	public void print(@RequestParam String id, HttpServletResponse response) throws JRException, IOException {
		Optional<Book> book = bookService.findById(UUID.fromString(id));

		InputStream jasperStream = this.getClass().getResourceAsStream("/reports/bookLabel.jasper");
		Map<String, Object> params = new HashMap<>();
		params.put("bookName", book.get().getName());
		params.put("authorName", book.get().getAuthor().getName());
		
		String isbnCode = book.get().getIsbnCode().replaceAll("-", "");
		isbnCode = isbnCode.substring(1, isbnCode.length()-2);
		//isbnCode = isbnCode.substring(1);
		params.put("isbnCode", isbnCode);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=" + book.get().getName().replaceAll(" ", "_") + "_label.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}

}
