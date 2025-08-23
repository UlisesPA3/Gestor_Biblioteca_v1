package filter;

import domain.Book;

public class AvailableFilter implements BookFilter{

    @Override
    public boolean apply(Book book) {
        return book.isAvailable();
    }
}
