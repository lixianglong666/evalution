package zut.software.evaluation.API;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 * 1.根据百度EasyDL平台的示例代码进行token的获取，发布模型会有一个APIKEY和SERECTKEY
 * 2.集成百度API会调用请求EasyDL文本分类接口（将请求URL和access_token以及文本拼接），用eresult接受结果，返回的是JSON对象
 * 3.将json对象转换为string，由于返回的results为结果值和置信度，所以要判断置信度小于1时才获取情感倾向结果。
 */
//accesstoken=24.cb14bcb6318752f55142af2889e0b119.2592000.1651663708.282335-25904623
public class AuthService {
public static  String APIKEY="sFsUhQjmoj16HuhNhBUgwGP1";
    public static  String SERECTKEY="gkU8xFn4DsXUH20HX7oRNa6m0t8nMVck";
public  static String URL="https://aip.baidubce.com/rpc/2.0/ai_custom/v1/sentiment_cls/reviewpro";
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "sFsUhQjmoj16HuhNhBUgwGP1";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "gkU8xFn4DsXUH20HX7oRNa6m0t8nMVck";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Secret Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

}