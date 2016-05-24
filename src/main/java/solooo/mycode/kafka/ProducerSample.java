package solooo.mycode.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/19
 * History:
 * his1:
 */
public class ProducerSample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.22:9092,192.168.1.23:9092");
        props.put("group.id", "test");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "solooo.mycode.kafka.MyPartitioner");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("pj-test", Integer.toString(i), "消息：" + Integer.toString(i));
            producer.send(record);
            System.out.println(record);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        producer.close();
    }
}
