package edu.miu.cs.cs425.demo.mybooksmgmtdbapp.repository;

import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
