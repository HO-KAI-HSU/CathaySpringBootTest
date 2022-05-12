package com.example.cathayspringboottest.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 接口使用 GET
     */
    public Map jsonGETNotAuth(String getUrL) {
        try {
            URL url = new URL(getUrL);    // 把字符串轉為URl請求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打開請求
            connection.connect();// 連接中
            // 取得串流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 讀取串流
                sb.append(line);
            }
            br.close();// 關閉串流
            connection.disconnect();// 斷開連結
            String txt = sb.toString();
            if ("".equals(txt)||txt.length()<3){
                return null;
            }
            Map result = new HashMap();
            if (txt.startsWith("{")&&txt.length()>3){
                result.put("flag",0); //map
                result.put("body", JSON.parse(txt));
                return result;
            }
            if (txt.startsWith("[")&&txt.length()>3){
                result.put("flag",1); //list
                result.put("body",JSON.parseArray(txt));
                return result;
            }
            return null;
        } catch (Exception e) {
            logger.error("get失敗!  "+e.toString());
            return null;
        }
    }
}
