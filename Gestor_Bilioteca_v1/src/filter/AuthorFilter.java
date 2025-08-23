package filter;

import domain.Book;

public class AuthorFilter implements BookFilter{
    private final String author;

    public AuthorFilter(String author) {
        this.author = author;
    }

    @Override
    public boolean apply(Book book) {
        return book.getAuthor().equals(author);
    }
}
