package solooo.mycode.springjms;

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
public class SendMessageTask implements Runnable {

    private ProducerService producerService;

    private Destination destination;

    long count = 0;

    @Override
    public void run() {
        while (true) {
            producerService.sendMessage(destination, "来自" + Thread.currentThread().getName() + "消息" + count);
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ProducerService getProducerService() {
        return producerService;
    }

    public void setProducerService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}