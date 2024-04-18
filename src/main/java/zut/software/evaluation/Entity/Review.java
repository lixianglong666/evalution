package zut.software.evaluation.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String reviewid;
    @Field
    private String userid;
    @Field
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    @Field
    private String phonename;
    @Field
    private String phoneid;
    @Field
    private String content;

    public int getEmotion() {
        return emotion;
    }

    public void setEmotion(int emotion) {
        this.emotion = emotion;
    }

    @Field
    private int emotion;
    @Field
    private String emotionqx;
    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




    public String getEmotionqx() {
        return emotionqx;
    }

    public void setEmotionqx(String emotionqx) {
        this.emotionqx = emotionqx;
    }


}
