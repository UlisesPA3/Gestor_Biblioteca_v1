package filter;

import domain.Book;

public interface BookFilter {
    boolean apply(Book book);
}
