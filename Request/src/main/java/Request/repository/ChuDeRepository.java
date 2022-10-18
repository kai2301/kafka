package Request.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import Request.model.ChuDe;

@Repository
public interface ChuDeRepository extends MongoRepository<ChuDe, String> {

}
