package store;

import model.Book;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BookStore {
    private static BookStore instance;

    private Map<String, Book> books;

    private BookStore() {
    }

    public synchronized static void initialize() {
        if (instance == null) {
            instance = new BookStore();
            instance.books = new HashMap<String, Book>();
        }
    }

    public static BookStore getInstance() {
        if (instance == null) {
            initialize();
        }
        return instance;
    }

    public void addBook(Book book) {
        instance.books.put(book.generateKey(), book);
    }

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableCollection(instance.books.values());
    }

    public Book getBook(String author, String title) {
        Book result = null;
        if (instance.books.containsKey(author + title)) {
            result = instance.books.get(author + title);
        }
        return result;
    }
}
