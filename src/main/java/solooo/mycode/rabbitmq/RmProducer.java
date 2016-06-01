package solooo.mycode.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/25
 * History:
 * his1:
 */
public class RmProducer {



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rabbitmq.xml");
        AmqpTemplate amqpTemplate = context.getBean(RabbitTemplate.class);
        amqpTemplate.convertAndSend("pj_test", "test.....");
    }
}
