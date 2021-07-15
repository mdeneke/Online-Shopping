package edu.miu.cs.cs425.webapps.elibrary.service.impl;

import edu.miu.cs.cs425.webapps.elibrary.model.Book;
import edu.miu.cs.cs425.webapps.elibrary.model.Publisher;
import edu.miu.cs.cs425.webapps.elibrary.repository.BookRepository;
import edu.miu.cs.cs425.webapps.elibrary.service.BookService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public List<Book> searchBooks(String searchString) {
        if(containsDecimalPoint(searchString) && isMoney(searchString)) {
            return bookRepository.findAllByOverdueFeeEquals(Double.parseDouble(searchString));
        } else if(isDate(searchString)) {
            return bookRepository.findAllByDatePublishedEquals(LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE));
        } else {
            return bookRepository.findAllByIsbnContainingOrTitleContaining(searchString, searchString);
        }
    }


    private boolean isDate(String searchString) {
        boolean isParseableAsDate = false;
        try {
            LocalDate.parse(searchString, DateTimeFormatter.ISO_DATE);
            isParseableAsDate = true;
        } catch(Exception ex) {
            if(ex instanceof DateTimeParseException) {
                isParseableAsDate = false;
            }
        }
        return isParseableAsDate;
    }

    private boolean isMoney(String searchString) {
        boolean isParseableAsMoney = false;
        try {
            Double.parseDouble(searchString);
            isParseableAsMoney = true;
        } catch(Exception ex) {
            if(ex instanceof ParseException) {
                isParseableAsMoney = false;
            }
        }
        return isParseableAsMoney;
    }

    private boolean containsDecimalPoint(String searchString) {
        return searchString.contains(".");
    }
}
