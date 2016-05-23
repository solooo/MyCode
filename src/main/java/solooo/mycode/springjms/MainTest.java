package solooo.mycode.springjms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Destination;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/19
 * History:
 * his1:
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-bean.xml");
        ProducerService producerService = (ProducerService) context.getBean("producerServiceImpl");
        Destination destination = (Destination) context.getBean("queueDestination");

        SendMessageTask sendMessageTask = new SendMessageTask();
        sendMessageTask.setProducerService(producerService);
        sendMessageTask.setDestination(destination);

        for (int i = 0; i < 3000; i++) {
            new Thread(sendMessageTask).start();
        }
    }
}
