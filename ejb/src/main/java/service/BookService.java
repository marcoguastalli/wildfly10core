package service;

import model.Book;
import model.BookCreateRequest;

import java.util.Collection;

public interface BookService {

    public Book createBasicBook(BookCreateRequest bookCreateRequest);

    public Collection<Book> getAllBooks();

    public Book getBook(String author, String title);

}
