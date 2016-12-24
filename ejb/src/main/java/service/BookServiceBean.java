package service;

import model.Book;
import model.Book.BookBuilder;
import model.BookCreateRequest;
import store.BookStore;


import java.util.Collection;

public class BookServiceBean implements BookService {
    private final BookStore bookStore;

    public BookServiceBean() {
        this.bookStore = BookStore.getInstance();
    }

    public Book createBasicBook(BookCreateRequest bookCreateRequest) {
        Book result = new BookBuilder(bookCreateRequest.getAuthor(), bookCreateRequest.getTitle()).build();
        bookStore.addBook(result);
        return result;
    }

    public Collection<Book> getAllBooks() {
        return bookStore.getAllBooks();
    }

    public Book getBook(String author, String title) {
        return bookStore.getBook(author, title);
    }

}
