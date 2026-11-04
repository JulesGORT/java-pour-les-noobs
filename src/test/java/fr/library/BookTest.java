package fr.library;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class BookTest {

    @Test
    void isbnNullShouldNotCreateBook(){
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Book(null, "test","test", 2000);
        });

        assertEquals("ISBN n'as pas le bon format", exception.getMessage());
    }

//    @Test
//    void isbnSpaceShouldNotCreateBook(){
//        String isbn = " ";
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            new Book(isbn, "test","test", 2000);
//        });
//
//        assertEquals("ISBN n'as pas le bon format", exception.getMessage());
//    }
//
//    @Test
//    void isbnWithLetterShouldNotCreateBook(){
//        String isbn = "aaaa";
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            new Book(isbn, "test","test", 2000);
//        });
//
//        assertEquals("ISBN n'as pas le bon format", exception.getMessage());
//    }

    @ParameterizedTest
    @ValueSource(strings = { "", "aaaa" })
    void shouldNotCreateBook(String isbn) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            new Book(isbn, "test","test", 2000);
        });
        assertEquals("ISBN n'as pas le bon format", exception.getMessage());
    }

    @Test
    void sameIsbnShouldEqualsBook(){
        String isbn = "1111111111111";
        var book1 = new Book(isbn, "test", "test", 2000);
        var book2 = new Book(isbn, "test2", "test2", 2000);

        assertEquals(book1, book2);
    }
}
