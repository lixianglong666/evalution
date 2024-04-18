package zut.software.evaluation.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import zut.software.evaluation.Entity.Phone;


@Repository
public interface PhoneRepository extends MongoRepository<Phone,String> {
}
