package solooo.mycode.springjms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/18
 * History:
 * his1:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-bean.xml"})
public class ProducerConsumerTest {


    @Autowired
    private ProducerService producerService;

    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void testSend() {
        SendMessageTask sendMessageTask = new SendMessageTask();
        sendMessageTask.setDestination(destination);
        sendMessageTask.setProducerService(producerService);
        for (int i=0; i<10; i++) {
            new Thread(sendMessageTask).start();
//            producerService.sendMessage(destination, "消息：" + i);
        }
    }


}
