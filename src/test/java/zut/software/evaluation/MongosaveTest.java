package zut.software.evaluation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import zut.software.evaluation.Entity.Phone;
import zut.software.evaluation.Entity.Review;
import zut.software.evaluation.Entity.User;
import zut.software.evaluation.repository.PhoneRepository;
import zut.software.evaluation.repository.ReviewRepository;
import zut.software.evaluation.repository.UserRepository;

public class MongosaveTest extends  EvaluationApplicationTests{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    public void testSave()
    {
        User user=new User();
        user.setId("55");
        user.setPassword("22222");
        user.setName("李");
        user.setAge(18);
        user.setSex("男");
        userRepository.insert(user);
    }
    @Test
    public void testSave1(){
        Review review=new Review();
        review.setReviewid("1");
        review.setUserid("1");
        review.setPhoneid("1");
        review.setContent("我好喜欢这部手机");
        review.setEmotion(0);
        reviewRepository.insert(review);
    }

    @Test
    public void testSave2(){
        Phone phone=phoneRepository.findById("3").get();
        phone.setCommoditytp("https://img30.360buyimg.com/n7/jfs/t1/147442/32/21307/435787/618ce7cbE95305e83/9cbb8f229dd7e0eb.jpg");
        phoneRepository.save(phone);

    }

    /* @Test
   public void text(){
        List<Phone> list= Arrays.asList(
                new Phone("3","","1522","1999",0),
                new Phone("4","小米2","1523","1999",0),
                new Phone("5","小米3","1524","1999",0),
                new Phone("6","小米4","1525","1999",0),
                new Phone("7","小米5","1526","1999",0),
                new Phone("8","小米6","1527","1999",0),
                new Phone("9","小米7","1528","1999",0),
                new Phone("10","小米8","1529","1999",0)
        );
        mongoTemplate.insert(list,Phone.class);
    }*/
}
