package solooo.mycode.resttemplate;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.web.client.RestTemplate;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/26
 * History:
 * his1:
 */
public class SpringRestTemplateTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
//        String result21 = restTemplate.getForObject("http://192.168.1.21:8080/v2/apps/tomcat2", String.class);
//        System.out.println(result21);
//        String result25 = restTemplate.getForObject("http://192.168.1.25:5051/metrics/snapshot", String.class);
//        System.out.println(result25);
        String query = "{ \"sort\": [ { \"createTime\": \"desc\" } ], \"query\": { \"bool\": { \"must_not\": [], \"should\": [], \"must\": [ { \"query_string\": { \"default_field\": \"content\", \"query\": \"第一\" } } ] } }, \"facets\": {}, \"from\": 0, \"size\": 10}";
        String result = "{\"message\":\"Unsupported Media Type\"}";
        JsonObject obj = new Gson().fromJson(result, JsonObject.class);
        JsonElement errorMsg = obj.get("message");
        System.out.println(errorMsg);
    }


}
