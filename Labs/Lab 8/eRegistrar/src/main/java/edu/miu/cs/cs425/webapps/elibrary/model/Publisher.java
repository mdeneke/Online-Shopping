package edu.miu.cs.cs425.webapps.elibrary.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PUBLISHERS")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int publisherId;

    @NotBlank(message = "* Publisher Name is required")
    private String name;

    private String contactPhoneNumber;

    @NotBlank(message = "* Email Address is required")
    private String emailAddress;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Publisher() {
    }

    public Publisher(String name, String contactPhoneNumber, String emailAddress) {
        this.name = name;
        this.contactPhoneNumber = contactPhoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<Book> getProducts() {
        return books;
    }

    public void setProducts(List<Book> products) {
        this.books = products;
    }
}
