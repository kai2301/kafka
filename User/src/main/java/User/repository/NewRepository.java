package User.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import User.model.News;
import User.model.User;

@Repository
public interface NewRepository extends MongoRepository<News, String> {

}