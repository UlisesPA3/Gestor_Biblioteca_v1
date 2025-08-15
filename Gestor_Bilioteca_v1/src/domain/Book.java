package domain;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book() {
    }

    public Book(String author, String isbn, String title, int year) {
        this.author = author;
        this.available = true;
        this.isbn = isbn;
        this.title = title;
        this.year = year;
    }

    //getters and setters
    public void setAvailable (boolean available){
        this.available  = available;
    }
    public String getIsbn(){
        return isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public int getYear(){
        return year;
    }
    public boolean isAvailable(){
        return available;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title){
        if(title == null || title.isEmpty()) throw new LibraryException("Titulo no puede estar vacio.");
        this.title = title;
    }
    public void setAuthor(String author){
        if(author == null || author.isEmpty()) throw new LibraryException("Autor no puede estar vacio.");
    }
    public void setYear(int year){
        if (year < 0) throw new LibraryException("El anio no puede ser negativo.");
        this.year = year;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                " | Titulo: " + title +
                " | Autor: " + author +
                " | Anio: " + year +
                " | Estado: " + (available ? "Disponible" : "Prestado");
    }
}
