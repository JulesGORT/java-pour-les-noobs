package fr.library.exceptions;

public class DuplicateBookException extends LibraryExceptions{
    public DuplicateBookException(String isbn) {
        super("Cette isbn : " + isbn + " est deja présent dans la liste");
    }
}
