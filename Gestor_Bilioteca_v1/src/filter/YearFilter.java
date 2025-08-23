package filter;

import domain.Book;

public class YearFilter implements BookFilter{
    private final int year;

    public YearFilter(int year) {
        this.year = year;
    }

    @Override
    public boolean apply(Book book) {
        return book.getYear() == year;
    }
}
