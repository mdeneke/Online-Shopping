package edu.miu.cs.cs425.webapps.elibrary.service;

import edu.miu.cs.cs425.webapps.elibrary.model.Book;
import edu.miu.cs.cs425.webapps.elibrary.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {

    public abstract List<Book> getBooks();
    public abstract Book saveBook(Book book);
    public abstract Book getBookById(Long bookId);
    public abstract void deleteBookById(Long bookId);
    public abstract List<Book> searchBooks(String searchString);



}
