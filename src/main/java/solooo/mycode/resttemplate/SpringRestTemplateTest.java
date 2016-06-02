package solooo.mycode.resttemplate;

import com.google.gson.Gson;

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
//        RestTemplate restTemplate = new RestTemplate();
//        String result21 = restTemplate.getForObject("http://192.168.1.21:8080/v2/apps/tomcat2", String.class);
//        System.out.println(result21);
//        String result25 = restTemplate.getForObject("http://192.168.1.25:5051/metrics/snapshot", String.class);
//        System.out.println(result25);
        String msg = "{\"type\":\"DOCKER\",\"docker\":{\"image\":\"tomcat\",\"forcePullImage\":false,\"privileged\":false}}";
        TopicMessageObject topicMessageObject = new Gson().fromJson(msg, TopicMessageObject.class);
        System.out.println(topicMessageObject.getContent());
    }


}
