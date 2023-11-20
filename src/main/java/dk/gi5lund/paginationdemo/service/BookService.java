package dk.gi5lund.paginationdemo.service;

import dk.gi5lund.paginationdemo.entity.Book;
import dk.gi5lund.paginationdemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Page<Book> getAllBooks(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
	public Page<Book> getBooksByAuthor(Pageable pageable, String author) {
		return  bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
	}
	public Page<Book> getBooksByTitleAndAuthor(Pageable pageable, String title, String author) {
		return bookRepository.findByTitleAndAuthorContainingIgnoreCase(title, author, pageable);
	}
	public Page<Book> getBooksByTitle(Pageable pageable, String title) {
		return bookRepository.findByTitleLikeIgnoreCase(title, pageable);
	}
}
