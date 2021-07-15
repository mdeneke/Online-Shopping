package edu.miu.cs.cs425.webapps.elibrary.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotNull(message = "ISBN is required")
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotNull(message = "Title is required")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "* Date supplied is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePublished;
    private Double overdueFee;


    @ManyToOne()
    @JoinColumn(name = "publisherId", nullable = false)
    @NotNull(message = "* Publisher is required")
    private Publisher publisher;


    public Book(Long bookId, String isbn, String title, LocalDate datePublished, Double overdueFee) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.datePublished = datePublished;
        this.overdueFee = overdueFee;
    }

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public Double getOverdueFee() {
        return overdueFee;
    }

    public void setOverdueFee(Double overdueFee) {
        this.overdueFee = overdueFee;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
