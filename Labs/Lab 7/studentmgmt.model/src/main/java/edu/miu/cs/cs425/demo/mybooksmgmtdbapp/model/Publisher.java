package edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer publisherId;
    private String name;

    @OneToMany(mappedBy = "publishedBy", cascade = CascadeType.PERSIST)
    private List<Book> booksPublished;

    public Publisher(Integer publisherId, String name, List<Book> booksPublished) {
        this.publisherId = publisherId;
        this.name = name;
        this.booksPublished = booksPublished;
    }

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher() {
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksPublished() {
        return booksPublished;
    }

    public void setBooksPublished(List<Book> booksPublished) {
        this.booksPublished = booksPublished;
    }
}
