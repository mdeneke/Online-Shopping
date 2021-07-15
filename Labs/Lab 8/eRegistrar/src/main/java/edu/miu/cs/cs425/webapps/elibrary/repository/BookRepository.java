package edu.miu.cs.cs425.webapps.elibrary.repository;

import edu.miu.cs.cs425.webapps.elibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Using Method Query
    public abstract List<Book> findAllByIsbnContainingOrTitleContaining(String isbn, String title);
    public abstract List<Book> findAllByDatePublishedEquals(LocalDate datePublished);
    public abstract List<Book> findAllByOverdueFeeEquals(Double overdueFee);

}
