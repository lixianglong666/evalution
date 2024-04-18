package zut.software.evaluation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import zut.software.evaluation.Entity.Eresult;
import zut.software.evaluation.Util.HttpUtil;

public class Evaluationtest {
    static final String TEXT_CLS_API ="https://aip.baidubce.com/rpc/2.0/ai_custom/v1/sentiment_cls/reviewpro";
    static final String ACCESS_TOKEN = "24.cb14bcb6318752f55142af2889e0b119.2592000.1651663708.282335-25904623";
    public static void main(String[] args) throws Exception {
        String text = "我不喜欢这部手机，充电太慢了";
        String result = getTextClsResult(ACCESS_TOKEN, text);
        //{"log_id":8814638451936454997,"results":[{"name":"negative","score":0.9999691247940063},{"name":"positive","score":3.091264079557732e-05}]}
        //String e=result.
        JSONObject jsonObject=new JSONObject(result);
        String resultjson=jsonObject.getString("results");
        JSONArray results=new JSONArray(resultjson);
        System.out.println(jsonObject.getString("log_id"));
        for(int i=0;i<results.length();i++)
        {
            JSONObject r=results.getJSONObject(i);
            double score=Double.valueOf(r.getString("score"));
            if(score<1)
            {
                System.out.println(r.getString("name"));
                break;
            }
        }


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
        System.out.println(result);
        return result;
    }
}
