package fr.library;

import fr.library.criterias.ByAuthor;
import fr.library.criterias.ByTitle;
import fr.library.criterias.ByYearRange;
import fr.library.criterias.SearchCriteria;
import fr.library.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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
    
    private static Stream<Arguments> criteriasSearch(){
        return Stream.of(
                Arguments.of(new ByTitle("clean code"), List.of(new Book("1111111111111", "clean code", "autheur test", 2000))),
                Arguments.of(new ByAuthor("autheur test"), List.of(new Book("1111111111111", "clean code", "autheur test", 2000))),
                Arguments.of(new ByYearRange(1999, 2002), List.of(new Book("1111111111111", "clean code", "test", 2000), 
                        new Book("2222222222222", "test title", "test", 2001)))
        );
    }

    @ParameterizedTest
    @MethodSource("criteriasSearch")
    void shouldNotFindBookByTitle(SearchCriteria criteria, List<Book> expected) {
        var library = new Library();
        library.addBook(
                new Book("1111111111111", "clean code", "autheur test", 2000)
        );
        library.addBook(
                new Book("2222222222222", "test title", "test", 2001)
        );
        assertEquals(expected, library.search(criteria));
    }
}