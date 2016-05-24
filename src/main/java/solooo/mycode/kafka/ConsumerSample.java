package solooo.mycode.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/20
 * History:
 * his1:
 */
public class ConsumerSample implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new ConsumerSample()).start();
        }

    }

    @Override
    public void run() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.1.22:9092,192.168.1.23:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("pj-test"));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("partition = %d, offset = %d, key = %s, value = %s", record.partition(), record.offset(), record.key(), record.value());
                System.out.println();
            }
        }
    }

}
