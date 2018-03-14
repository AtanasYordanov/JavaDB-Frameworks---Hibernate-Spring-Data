package BookLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private Map<String, ArrayList<Book>> authorsAndBooks;
    private ArrayList<Book> books;

    Library(String name, ArrayList<Book> books) {
        this.books = books;
        authorsAndBooks = new HashMap<>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public Map<String, ArrayList<Book>> getAuthorsAndBooks() {
        return authorsAndBooks;
    }
}
