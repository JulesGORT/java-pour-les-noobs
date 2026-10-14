package fr.library;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private int year;
    private String isbn;

    public Book(String isbn, String title, String author, int year) {
        if(!verifyIsbn(isbn)){
            throw new IllegalArgumentException();
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    private boolean verifyIsbn(String isbn) {
        return isbn != null && !isbn.isEmpty() && isbn.matches("\\d{13}");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Book book = (Book) obj;
        return Objects.equals(this.isbn, book.isbn);
    }

    @Override
    public int hashCode(){
        return Integer.parseInt(this.isbn);
    }

    @Override
    public String toString(){
        return "title : " + this.title + ", author : " + this.author + ", year : " + this.year;
    }
}
