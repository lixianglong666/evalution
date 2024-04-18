package zut.software.evaluation.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.annotation.sql.DataSourceDefinition;
import java.util.Date;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {


    //账号
    @Id
    private String id;
    /*
    * 密码
    * */
    @Field
    private String password;
    /**
     * 姓名
     * */
    @Field
    private String name;
    @Field
    private String telephone;
    @Field
    private String sex;
    @Field
    private int age;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
