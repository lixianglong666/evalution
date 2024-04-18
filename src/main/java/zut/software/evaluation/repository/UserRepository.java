package zut.software.evaluation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import zut.software.evaluation.Entity.User;
/*
* 整个ORM用的是类似JPA的东西，MongoRepository
* */
@Repository
public interface UserRepository extends MongoRepository<User,String> {
    //public Page<User> findByUserNameLike(String name, Pageable pageable);
}
