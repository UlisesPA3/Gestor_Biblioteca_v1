package service;

import domain.Book;
import domain.LibraryException;
import repository.BookRepository;

public class LoanManager {
    private final BookRepository booksRepository;

    public LoanManager(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public void lendBook(String isbn){
        Book book = booksRepository.findBookByIsbn(isbn);
            if(!book.isAvailable()){
                throw new LibraryException("El libro ya esta prestado: " + isbn);
            }
            book.setAvailable(false);
        }
    public void returnBook(String isbn){
        Book book = booksRepository.findBookByIsbn(isbn);
        if(book.isAvailable()){
            throw new LibraryException("El libro ya esta disponible, no puede devolverse" + isbn);
        }
        book.setAvailable(true);
    }
}
