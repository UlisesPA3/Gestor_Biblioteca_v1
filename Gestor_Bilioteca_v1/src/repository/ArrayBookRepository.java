package repository;

import domain.Book;
import domain.LibraryException;

import java.util.ArrayList;
import java.util.List;

public class ArrayBookRepository implements BookRepository{
    private final Book[] books;
    private int count;

    public ArrayBookRepository(int size) {
        this.books = new Book[size];
        this.count = 0;
    }

    @Override
    public void addBook(Book book) {
        if(count >= books.length){
            throw new LibraryException("Repositorio lleno, no se puede agregar mas libros");
        }
        if(findBookByIsbnOrNull(book.getIsbn()) != null){
            throw new LibraryException("El ISBN ya existe: " + book.getIsbn());
        }
        books[count++] = book;
    }

    @Override
    public void removeBook(String isbn) {
        for(int i = 0; i < count; i++){
            if(books[i].getIsbn().equals(isbn)){
                for(int j = i; j < count - 1; j++){
                    books[j] = books[j + 1];
                }
                books[--count] = null;
                return;
            }
        }
        throw new LibraryException("Libro no encontrado con ISBN: " + isbn);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
       for(int i = 0; i< count; i++){
           if(books[i].getIsbn().equals(isbn)){
               return books[i];
           }
       }
       throw new LibraryException("Libro no encontrado con ISBN: " + isbn);
    }

    private Book findBookByIsbnOrNull(String isbn){
        for(int i = 0; i< count; i++){
            if(books[i].getIsbn().equals(isbn)){
                return books[i];
            }
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> result = new ArrayList<>();
        for(int i = 0; i < count; i ++){
            result.add(books[i]);
        }
        return result;
    }
}
