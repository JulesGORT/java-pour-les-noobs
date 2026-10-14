package fr.library;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        try {
            library.addBook(new Book("9780132350884", "Clean Code", "Robert Martin", 2008));
            library.addBook(new Book("9780134685991", "Effective Java", "Joshua Bloch", 2018));
            library.addBook(new Book("9780134685991", "Effective Java", "Joshua Bloch", 2018));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            library.removeBook("9780134685991");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        library.displayBooks();
    }
}
