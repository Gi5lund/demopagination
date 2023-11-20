package dk.gi5lund.paginationdemo.api;


import dk.gi5lund.paginationdemo.entity.Book;
import dk.gi5lund.paginationdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public Page<Book> getBooks(@RequestParam(value="author", required = false) String author, @RequestParam(value="title", required = false) String title, Pageable pageable) {
		System.out.println("pageable = " + pageable);
		if(author != null&title==null) {
			return bookService.getBooksByAuthor(pageable, author);
		}
		if(title != null&author!=null) {
			return bookService.getBooksByTitleAndAuthor(pageable, title, author);
		}
		if(title != null&author==null) {
			return bookService.getBooksByTitle(pageable, title);
		}
		return bookService.getAllBooks(pageable);
	}
//	@GetMapping(params = "author")
//	public Page<Book> getBooksByAuthor(@RequestParam String author, Pageable pageable) {
//		System.out.println("pageable = " + pageable); //Add a breakpoint here, and investigate the pageable instance
//		return bookService.getAllBooks(pageable);
//	}
}
