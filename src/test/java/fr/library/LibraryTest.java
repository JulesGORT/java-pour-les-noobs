package fr.library;

import fr.library.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LibraryTest {


    @Test
    void shouldAddBooks() {
        Library library = new Library();
        library.addBook(new Book("1111111111111", "test", "test", 2000));
        assertEquals(1, library.size());
    }

    @Test
    void shouldFindBookByTitle() {
        Library library = new Library();
        library.addBook(new Book("9780132350884", "Clean Code", "Robert C. Martin", 2008));

        Book book = library.findBookByTitle("Clean Code");

        assertEquals("9780132350884", book.getIsbn());
    }

    @Test
    void shouldNotFindBookByTitle() {
        Library library = new Library();
        String titre = "Clean code";
        Exception exception = assertThrows(BookNotFoundException.class, () -> {
            library.findBookByTitle(titre);
        });

        assertEquals("y a pas de book a ce titre : " + titre, exception.getMessage());
    }
}