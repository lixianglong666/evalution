package zut.software.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import zut.software.evaluation.Entity.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review,String> {


   List<Review> findByPhoneid(String phoneid);
    List<Review> findByUserid(String id);

}
