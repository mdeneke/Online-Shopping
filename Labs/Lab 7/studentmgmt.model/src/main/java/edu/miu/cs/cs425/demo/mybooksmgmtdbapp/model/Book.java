package edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @NotNull(message = "ISBN is required")
    //@NotEmpty
    @NotBlank(message = "ISBN is required and cannot be blank/spaces")
    private String isbn;

    private String title;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable = true)
    private Publisher publishedBy;

    public Book(Integer bookId, String isbn, String title, Publisher publishedBy) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.publishedBy = publishedBy;
    }

    public Book(String isbn, String title, Publisher publishedBy) {
        this.isbn = isbn;
        this.title = title;
        this.publishedBy = publishedBy;
    }

    public Book() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublishedBy() {
        return publishedBy;
    }

    public void setPublishedBy(Publisher publishedBy) {
        this.publishedBy = publishedBy;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("bookId=" + bookId)
                .add("isbn='" + isbn + "'")
                .add("title='" + title + "'")
                .toString();
    }
}
