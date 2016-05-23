package solooo.mycode.springjms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/18
 * History:
 * his1:
 */
public class ConsumerMessageListener implements MessageListener {

    public void onMessage(Message message) {
        //这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;
        try {
            System.out.println("接收消息：" + textMsg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
