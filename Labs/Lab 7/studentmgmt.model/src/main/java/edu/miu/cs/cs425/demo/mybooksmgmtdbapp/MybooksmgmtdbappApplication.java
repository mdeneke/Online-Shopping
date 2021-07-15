package edu.miu.cs.cs425.demo.mybooksmgmtdbapp;

import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model.Book;
import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model.Publisher;
import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.repository.BookRepository;
import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class MybooksmgmtdbappApplication implements CommandLineRunner {

    //@Autowired
    private BookRepository bookRepository;

    private PublisherRepository publisherRepository;

//    @Autowired
    public MybooksmgmtdbappApplication(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MybooksmgmtdbappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello MyBooksMgmtDBApp!!!");
        Publisher p1 = new Publisher("McGraw-Hill");
        Book book1 = new Book("978-00001", "Introduction to Algorithms", p1);
        Book book2 = new Book("978-00002", "Advanced Software Design", p1);
        List<Book> books = Arrays.asList(book1, book2);
        p1.setBooksPublished(books);
        saveNewPublisher(p1);

        Publisher p2 = new Publisher("Apress");
        Book book3 = new Book("978-009887", "UML Distilled",p2);
        Book book4 = new Book("978-009886", "Database Design and Development",p2);
        List<Book>books2= Arrays.asList(book3, book4);
        p2.setBooksPublished(books2);
        saveNewPublisher(p2);

        Publisher p3 = new Publisher("Pearson Inc");
        Book book5 = new Book("978-009184", "HTML and CSS workbook",p3);
        List<Book>books3= Arrays.asList(book5);
        p3.setBooksPublished(books3);
        saveNewPublisher(p3);
    }

    private void saveNewBook(Book book) {
        System.out.println("Saving a new book");
        this.bookRepository.save(book);
    }

    private void saveNewPublisher(Publisher publisher) {
        System.out.println("Saving a new publisher");
        this.publisherRepository.save(publisher);
    }
}
