package service;

import domain.Book;
import repository.BookRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryReportGenerator {
    private final BookRepository bookRepository;

    public LibraryReportGenerator(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void printAllBooks(){
        List<Book> books = bookRepository.getAllBooks();
        if(books.isEmpty()){
            System.out.println("No hay libros registrados");
        }else{
            System.out.println("Lista de libros");
            books.forEach(System.out::println);
        }
    }

    public void printAvailableBooks(){
        List<Book> availableBooks = bookRepository.getAllBooks().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());

        System.out.println("Libros disponibles");
        availableBooks.forEach(System.out::println);
    }

    public void printBooksGroupeByAuthor(){
        Map<String, List<Book>> grouped = bookRepository.getAllBooks().stream()
                .collect(Collectors.groupingBy(Book::getAuthor));

        System.out.println("Libros agrupados por autor");
        for(String author : grouped.keySet()){
            System.out.println("- " + author + ":");
            grouped.get(author).forEach(book -> System.out.println(" " + book));
        }
    }
    public void printTotalCount(){
        System.out.println("Total de libros: " + bookRepository.getAllBooks().size());
    }
}
