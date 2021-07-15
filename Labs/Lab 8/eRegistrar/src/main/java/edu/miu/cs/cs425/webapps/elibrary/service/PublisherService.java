package edu.miu.cs.cs425.webapps.elibrary.service;

import edu.miu.cs.cs425.webapps.elibrary.model.Publisher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PublisherService {

    List<Publisher> getAllPublishers();
    Publisher addNewPublisher(Publisher publisher);
}
