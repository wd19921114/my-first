package cn.ppz111.creator.utils;

import cn.ppz111.creator.enums.ErrorEnum;
import cn.ppz111.creator.exception.CgErrorException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Yuki
 */
@Component
@Slf4j
public class AccessTokenUtil {
    private static StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        AccessTokenUtil.redisTemplate = redisTemplate;
    }

    public static String doPost(String url, String data) {
        HttpURLConnection connection = null;
        OutputStream out = null;
        InputStream in = null;
        int responseCode = 0;
        String result = "";
        try {
            URL sendUrl = new URL(url);
            connection = (HttpURLConnection) sendUrl.openConnection();
            //post方式请求
            connection.setRequestMethod("POST");
            //设置头部信息
            connection.setRequestProperty("headerdata", "ceshiyongde");
            //一定要设置 Content-Type 要不然服务端接收不到参数
            connection.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            //指示应用程序要将数据写入URL连接,其值默认为false（是否传参）
            connection.setDoOutput(true);

            connection.setUseCaches(false);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            //传入参数
            out = connection.getOutputStream();
            out.write(data.getBytes());
            out.flush(); //清空缓冲区,发送数据
            out.close();
            responseCode = connection.getResponseCode();
            //获取请求的资源
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            result = br.readLine();
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("status", "500");
            map.put("detail", "抱歉，服务器内部错误！");
            result = JSONObject.toJSONString(map);
            e.printStackTrace();
            throw new CgErrorException(ErrorEnum.USER_NOT_EXIST);
        }
        int code = (int) JSON.parseObject(result).get("errcode");
        if (code == 0) {
            return result;
        } else {
            return (String) JSON.parseObject(result).get("errmsg");
        }

    }

    public static String getAccessToken() {
        String accessToken = redisTemplate.opsForValue().get("access_token");
        if (StringUtils.isEmpty(accessToken)) {
            String appid = "wx2ea8c6500651cf4e";
            String secret = "7d7a81c74e042c1a496e2bad155c5bca";
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
            String num ;
            num = doGet(url);
            redisTemplate.opsForValue().set("access_token", (String) JSON.parseObject(num).get("access_token"), (int) JSON.parseObject(num).get("expires_in"), TimeUnit.SECONDS);
            return (String) JSON.parseObject(num).get("access_token");
        } else {
            return accessToken;
        }
    }

    public static String doGet(String url) {
        HttpURLConnection connection = null;
        OutputStream out = null;
        InputStream in = null;
        int responseCode = 0;
        String result = "";
        try {
            URL sendUrl = new URL(url);
            connection = (HttpURLConnection) sendUrl.openConnection();
            //post方式请求
            connection.setRequestMethod("GET");
            //设置头部信息
            connection.setRequestProperty("headerdata", "ceshiyongde");
            //一定要设置 Content-Type 要不然服务端接收不到参数
            connection.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            responseCode = connection.getResponseCode();
            if(responseCode==200){
                //获取请求的资源
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                result = br.readLine();
            }
        } catch (Exception e) {
            throw new CgErrorException(100000, e.getMessage());
        }
        return result;
    }
}
