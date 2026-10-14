package fr.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws Exception {
        if(this.containsIsbn(book.getIsbn())) throw new Exception("y a deja cet ISBN " + book.getIsbn());
        this.books.add(book);
    }

    public void displayBooks() {
        for(Book book : books){
            System.out.print("- ");
            System.out.println(book);
        }
    }

    public Optional<Book> findBookByTitle(String title) {
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
                .findFirst();
    }

    public void removeBook(String isbn) throws ClassNotFoundException {
        Book book = this.findByIsbn(isbn).orElseThrow(() -> new ClassNotFoundException("book non trouvé pour l'isbn : " + isbn));
        books.remove(book);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    public List<Book> findAllByAuthor(String author) {
        List<Book> booksResult = new ArrayList<>();
        for(Book book : this.books) {
            if(book.getAuthor().equals(author)){
                booksResult.add(book);
            }
        }
        return booksResult;
    }

    public boolean containsIsbn(String isbn) {
        return books.stream()
                .anyMatch(book -> book.getIsbn().equals(isbn));
    }

    public int size(){
        return this.books.size();
    }


}
