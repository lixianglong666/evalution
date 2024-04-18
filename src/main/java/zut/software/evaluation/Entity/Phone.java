package zut.software.evaluation.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "phone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    /**
     * 增添规格，商品分类
     * */
    @Id
    private String phoneid;
    @Field
    private String phonename;
    @Field
    private String salenum;
    @Field
    private String price;
    @Field
    private String standard;
    @Field
    private String commodityfl;

    public String getCommoditytp() {
        return commoditytp;
    }

    public void setCommoditytp(String commoditytp) {
        this.commoditytp = commoditytp;
    }

    @Field
    private String commoditytp;
    @Field
    private double zhvalue;

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getCommodityfl() {
        return commodityfl;
    }

    public void setCommodityfl(String commodityfl) {
        this.commodityfl = commodityfl;
    }




    public String getPhoneid() {
        return phoneid;
    }

    public void setPhoneid(String phoneid) {
        this.phoneid = phoneid;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getSalenum() {
        return salenum;
    }

    public void setSalenum(String salenum) {
        this.salenum = salenum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getZhvalue() {
        return zhvalue;
    }

    public void setZhvalue(double zhvalue) {
        this.zhvalue = zhvalue;
    }




}
