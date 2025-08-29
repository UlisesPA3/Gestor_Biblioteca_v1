package service;

import domain.Book;
import filter.BookFilter;
import repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BookFilterService {
    private final BookRepository bookRepository;

    public BookFilterService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> filterBooks(BookFilter filter){
        return bookRepository.getAllBooks().stream().filter(filter::apply).collect(Collectors.toList());
    }
}
