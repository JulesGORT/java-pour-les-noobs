package fr.library;

import fr.library.criterias.ByAuthor;
import fr.library.criterias.ByTitle;
import fr.library.criterias.ByYearRange;
import fr.library.criterias.SearchCriteria;
import fr.library.exceptions.BookNotFoundException;
import fr.library.exceptions.DuplicateBookException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Library {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        if(this.containsIsbn(book.getIsbn())) throw new DuplicateBookException(book.getIsbn());
        this.books.add(book);
    }

    public void displayBooks() {
//        for(Book book : books){
//            System.out.print("- ");
//            System.out.println(book);
//        }
        books.forEach((book -> {
            System.out.print("- ");
            System.out.println(book);
        }));
    }

    public Book findBookByTitle(String title) {
        /*
        Book bookResult = null;
        for(Book book : books){
            if(book.getTitle().equals(title)) {
                bookResult = book;
            }
        }
        if(bookResult != null ) return Optional.of(bookResult);
        return Optional.empty();

        */

        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("y a pas de book a ce titre : " + title));
    }

    public void removeBook(String isbn){
        Book book = this.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("book non trouvé pour l'isbn : " + isbn));
        books.remove(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Book> findAllByAuthor(String author) {
        List<Book> booksResult = new ArrayList<>();
//        for(Book book : this.books) {
//            if(book.getAuthor().equals(author)){
//                booksResult.add(book);
//            }
//        }
        books.forEach(book -> {
            if(book.getAuthor().equals(author)){
                booksResult.add(book);
            }
        });
        return booksResult;
    }

    public boolean containsIsbn(String isbn) {
        return books.stream()
                .anyMatch(book -> book.getIsbn().equals(isbn));
    }

    public int size(){
        return this.books.size();
    }

    public List<Book> search(SearchCriteria criteria) {
        return books.stream()
                .filter(book -> switch (criteria) {
                    case ByTitle(String title) -> title.equalsIgnoreCase(book.getTitle());
                    case ByAuthor(String author) -> author.equalsIgnoreCase(book.getAuthor());
                    case ByYearRange(int from, int to) -> book.getYear() > from && book.getYear() < to;
                })
                .toList();
    }


}