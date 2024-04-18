package zut.software.evaluation;


import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import zut.software.evaluation.Entity.User;
import zut.software.evaluation.repository.UserRepository;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Mongodbtest extends EvaluationApplicationTests{
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    //UserRepository userRepository;



    @Test
    public void testCreateCollection(){
        boolean emp=mongoTemplate.collectionExists("user");
        if(emp) {
            mongoTemplate.dropCollection("user");
        }
    }
    @Test
    public void testInsert(){
        //User user1=new User("1","298811","张三","15139067338","男",10);
        //mongoTemplate.insert(user1);
        //mongoTemplate.save(user1);
        List<User> list= Arrays.asList(
                new User("10","10","校炎","122566662","女",78),
                new User("11","11","吞噬","122566669","男",18),
        new User("12","12","夕阳","122566664","男",18),
        new User("13","13","美羊","122566665","男",18),
        new User("14","14","懒洋","122566666","男",18),
        new User("15","15","沸羊","122566667","男",18),
        new User("16","16","慢羊","122566668","男",18),
        new User("17","17","暖洋","122566669","男",18),
        new User("18","18","灰太","122569661","男",18)
        );
        mongoTemplate.insert(list,User.class);
    }
    @Test
    public void testFind(){
        /*System.out.println("===查所有===");
        List<User> list=mongoTemplate.findAll(User.class);
        list.forEach(System.out::println);
        System.out.println("===根据id查===");
        User e=mongoTemplate.findById(1,User.class);
        System.out.println(e);*/
    //mongoTemplate.find(new Query(),User.class);

        System.out.println("===条件查询===");
        //Query query=new Query(Criteria.where("age").gte(20));//可以继续叠条件
        //复杂查询
        Criteria criteria=new Criteria();
        criteria.andOperator(Criteria.where("age").gt(1),Criteria.where("name").regex("张"));
        Query query=new Query(criteria);
        //query.with(Sort.by(Sort.Order.desc("age")));
        //分页
        query.with(Sort.by(Sort.Order.desc("age")))
                .skip(0)    //指定跳过记录数
                .limit(4); //每页显示记录数
        /*Query query=new Query(Criteria.where("name").regex("张"));*/
        List<User> users=mongoTemplate.find(query,User.class);
        users.forEach(System.out::println);
    }
    @Test
    public void  testFindByJson(){
        String json="{name:'张三'}";
        String json1="{$or:[{age:{$gt:25}},{salary:{$gte:8000}}]}";
        Query query=new BasicQuery(json);
        List<User> users=mongoTemplate.find(query,User.class);
        users.forEach(System.out::println);
    }
    @Test
    public void testUpdate(){
        Query query=new Query(Criteria.where("age").gte(80));
        System.out.println("===更新前===");
        List<User> users=mongoTemplate.find(query,User.class);
        users.forEach(System.out::println);
        Update update=new Update();
        update.set("age",11);
        //UpdateResult updateResult=mongoTemplate.updateFirst(query,update,User.class);
        //UpdateResult updateResult=mongoTemplate.updateMulti(query,update,User.class);
       update.setOnInsert("id",11);
        UpdateResult updateResult=mongoTemplate.upsert(query,update,User.class);
        System.out.println(updateResult.getModifiedCount());
        System.out.println("===更新后===");
        users=mongoTemplate.find(query,User.class);
        users.forEach(System.out::println);
    }
    @Test
    public void testDelete(){
        //dropCollection()
        //Query query=new Query(Criteria.where("age").gte(18));
        mongoTemplate.remove(new Query(),User.class);
    }
    @Test
    public void testdouble(){
        //double zhvalue=Double.valueOf(esum)/Double.valueOf(reviewlist.size());
        double a=3.1234;
        java.text.DecimalFormat df=new java.text.DecimalFormat("#.00");

        System.out.println(df.format(a));
    }
}

