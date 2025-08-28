package repository;

import domain.Book;
import domain.LibraryException;

import java.util.ArrayList;
import java.util.List;

public class ArrayListBookRepository implements BookRepository{

    private List<Book> books;

    public ArrayListBookRepository() {
        this.books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        if(findBookByIsbnOrNull(book.getIsbn()) != null){
            throw new LibraryException("El ISBN ya existe: " + book.getIsbn());
        }
        books.add(book);
    }

    @Override
    public void removeBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        books.remove(book);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return books.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst().orElseThrow(() -> new LibraryException("Libro no encontrado con ISBN: " + isbn));
    }

    private Book findBookByIsbnOrNull(String isbn){
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElseThrow(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}
