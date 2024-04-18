package zut.software.evaluation.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import zut.software.evaluation.Entity.Eresult;
import zut.software.evaluation.Entity.Phone;
import zut.software.evaluation.Entity.Review;
import zut.software.evaluation.Util.HttpUtil;
import zut.software.evaluation.repository.PhoneRepository;
import zut.software.evaluation.repository.ReviewRepository;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/evaluation/review")
public class Reviewcontroller {
    static final String TEXT_CLS_API ="https://aip.baidubce.com/rpc/2.0/ai_custom/v1/sentiment_cls/reviewpro";
    static final String ACCESS_TOKEN = "24.1193a2f376bffbb0afad39caa4177f3e.2592000.1654051747.282335-25904623";


    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @GetMapping(value = "/findbyid/{id}")
    public Optional<Review> findbyid(@PathVariable("id") String id){
        return reviewRepository.findById(id);
    }

    @GetMapping(value = "/findbyphoneid/{phoneid}")
    public List<Review> findbyphoneid(@PathVariable("phoneid") String phoneid){
       return reviewRepository.findByPhoneid(phoneid);
    }

    @GetMapping(value = "/findbyuserid/{id}")
    public List<Review> findbyuserid(@PathVariable("id") String id){
        return reviewRepository.findByUserid(id);
    }




    @GetMapping(value = "/findallreview/{page}/{size}")
    public Page<Review> readReviewsByPage(@PathVariable("page") Integer page,
                                        @PathVariable Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return reviewRepository.findAll(pageable);
    }
    /**
     * 添加（保存评论）
     * */
    @PostMapping(value = "/savereview")
    @ResponseBody
    public String saveReview(@RequestBody Review review) throws Exception {

        Eresult eresult=new Eresult();//用eresult接收返还数据
        if(review.getContent()==null)
            review.setEmotion(0);
        else
        {
            eresult=saveemotion(review.getContent());
            review.setEmotionqx(eresult.getName());
            if(review.getEmotionqx().equals("negative"))
                review.setEmotion(0);
            else
                review.setEmotion(1);
        }
        Optional<Review> test=reviewRepository.findById(review.getReviewid());
        if(test.isPresent())//判断评论ID是否重复
            return "chongfu";
        Review result=reviewRepository.insert(review);

        Optional<Phone> phone=phoneRepository.findById(review.getPhoneid());/*根据评论的phonid找到评论所在的手机*/
        List<Review> reviewlist=reviewRepository.findByPhoneid(phone.get().getPhoneid());/*将该手机下的评论全部找到，反向遍历，重新计算综合评价*/
        int esum=0;
        for (int i=0;i<reviewlist.size();i++)
        {
            esum+=reviewlist.get(i).getEmotion();
        }

        //double zhvalue=Double.valueOf(esum)+Double.valueOf(phone.get().getSalenum())/100;
        double pvalue=0;
        if(Integer.valueOf(phone.get().getPrice())<=1000)
        {
            pvalue=0.01;
        }
        else if(Integer.valueOf(phone.get().getPrice())<=3000)
        {
            pvalue=0.02;
        }else {
            pvalue=0.03;
        }
        double zhvalue=(Double.valueOf(esum))/Double.valueOf(reviewlist.size())+pvalue;//情感总和除以评论数量+价格权重，
        java.text.DecimalFormat df=new java.text.DecimalFormat("#.00");
        //df.format(zhvalue);
        phone.get().setZhvalue(Double.valueOf(df.format(zhvalue)));
        phone.get().setSalenum(reviewlist.size()+"");
        Phone result1=phoneRepository.save(phone.get());
        if(result!=null&&result1!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 集成百度API
     * 集成百度API会调用请求EasyDL文本分类接口（将请求URL和access_token以及文本拼接），用eresult接受结果，返回的是JSON对象
     * 将json对象转换为string，由于返回的results为结果值和置信度，所以要判断置信度小于1时才获取情感倾向结果。
     * */
    public Eresult saveemotion(String text)  throws Exception{
        Eresult eresult=new Eresult();
        //String text = "我不喜欢这部手机，充电太慢了";
        String result = getTextClsResult(ACCESS_TOKEN, text);//必要参数
        JSONObject jsonObject=new JSONObject(result);//将json对象转换为string
        String resultjson=jsonObject.getString("results");
        JSONArray results=new JSONArray(resultjson);
        System.out.println(jsonObject.getString("log_id"));
        for(int i=0;i<results.length();i++)
        {
            JSONObject r=results.getJSONObject(i);
            double score=Double.valueOf(r.getString("score"));
            if(score<1)
            {
                eresult.setName(r.getString("name"));
                eresult.setScore(Double.valueOf(r.getString("score")));
                break;
            }
        }
        return eresult;
    }
    /**
     * 请求EasyDL文本分类接口
     * @param accessToken 鉴权的token
     * @param text
     * @return String
     * @throws Exception
     */
    public static String getTextClsResult(String accessToken,String text) throws Exception {
        // 请将API地址替换为EasyDL所提供的API地址
        String url = TEXT_CLS_API;
        // access_token获取方法请详见API使用说明，请注意access_token有效期为30天
        String access_token = accessToken;
        // 返回分类数量top_num设置为5 如不设置默认返回6条结果
        String params = "{\"text\":\"" + text + "\"}";
        //String params = "{\"text\":\"" + text + "\",\"top_num\":5}";
        String result = HttpUtil.post(url, access_token, params);
        // 输出识别结果
       // System.out.println(result);
        return result;
    }




    /**
     * 修改
     * */
    @PutMapping("/updatereview")
    @ResponseBody
    public String updateReview(@RequestBody Review review){
        Review result=reviewRepository.save(review);
        if(result!=null)
            return "success";
        else
            return "error";
    }

    /**
     * 删除
     * */
    @DeleteMapping ("/deletebyid/{id}")
    @ResponseBody
    public void deleteReview(@PathVariable("id") String id){
        reviewRepository.deleteById(id);
    }
}
