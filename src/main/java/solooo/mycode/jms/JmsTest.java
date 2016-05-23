package solooo.mycode.jms;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/19
 * History:
 * his1:
 */
public class JmsTest {

    public static void main(String[] args) {
        ProducerThread producer = new ProducerThread();

        for (int i = 0; i < 3000; i++) {
            new Thread(producer).start();
        }

    }
}

class ProducerThread implements  Runnable {

    @Override
    public void run() {
        JmsProducer producer = new JmsProducer();
        producer.run();
    }
}

