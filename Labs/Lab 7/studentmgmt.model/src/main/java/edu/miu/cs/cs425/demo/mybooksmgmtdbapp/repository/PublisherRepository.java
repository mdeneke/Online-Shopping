package edu.miu.cs.cs425.demo.mybooksmgmtdbapp.repository;

import edu.miu.cs.cs425.demo.mybooksmgmtdbapp.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<Publisher, Integer> {
}
