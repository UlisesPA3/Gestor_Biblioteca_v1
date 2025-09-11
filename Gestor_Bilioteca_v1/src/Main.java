import domain.Book;
import domain.LibraryException;
import filter.AuthorFilter;
import filter.AvailableFilter;
import filter.YearFilter;
import repository.ArrayListBookRepository;
import repository.BookRepository;
import service.BookFilterService;
import service.LibraryReportGenerator;
import service.LoanManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookRepository repository = new ArrayListBookRepository();
        LoanManager loanManager = new LoanManager(repository);
        BookFilterService filterService = new BookFilterService(repository);
        LibraryReportGenerator reportGenerator = new LibraryReportGenerator(repository);

        while(true){
            System.out.println("\nSISTEMA DE GESTION DE BIBLIOTECA");
            System.out.println("1. Agregar libro");
            System.out.println("2. Eliminar libro");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Prestar libro");
            System.out.println("5. Devolver libro");
            System.out.println("6. Filtrar por autor");
            System.out.println("7. Filtrar por anio");
            System.out.println("8. Ver solo libros disponibles");
            System.out.println("9. Ver reporte Completo");
            System.out.println("0. Salir");
            System.out.println("Elige una opcion: ");

            String option = scanner.nextLine();

            try{
                switch (option){
                    case "1":
                        System.out.println("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Titulo: ");
                        String title = scanner.nextLine();
                        System.out.println("Autor: ");
                        String author = scanner.nextLine();
                        System.out.println("Anio: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        repository.addBook(new Book(isbn, title, author, year));
                        System.out.println("Libro agregado.");
                        break;
                    case "2":
                        System.out.println("ISBN del libro a eliminar: ");
                        repository.removeBook(scanner.nextLine());
                        System.out.println("Libro eliminado.");
                        break;
                    case "3":
                        reportGenerator.printAllBooks();
                        break;
                    case "4":
                        System.out.println("ISBN del libro a prestar: ");
                        loanManager.lendBook(scanner.nextLine());
                        System.out.println("Libro eliminado. ");
                        break;
                    case "5":
                        System.out.println("ISBN del libro a devolver: ");
                        loanManager.returnBook(scanner.nextLine());
                        System.out.println("Libro devuelto.");
                        break;
                    case "6":
                        System.out.println("Author: ");
                        String authorFilter = scanner.nextLine();
                        List<Book> booksByAuthor = filterService.filterBooks(new AuthorFilter(authorFilter));
                        booksByAuthor.forEach(System.out::println);
                        break;
                    case "7":
                        System.out.println("Anio: ");
                        int yearFilter = Integer.parseInt(scanner.nextLine());
                        List<Book> booksByYear = filterService.filterBooks(new YearFilter(yearFilter));
                        booksByYear.forEach(System.out::println);
                        break;
                    case "8":
                        List<Book> avilablesBooks = filterService.filterBooks(new AvailableFilter());
                        avilablesBooks.forEach(System.out::println);
                        break;
                    case "9":
                        System.out.println("Hasta la proxima!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opcion no valida");
                }

            }catch (LibraryException e){
                System.out.println("Error: " + e.getMessage());
            }catch (NumberFormatException e){
                System.out.println("Ingreso de opcion no vcalida");
            }
        }
    }
}