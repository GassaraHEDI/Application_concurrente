package com.example.demo;

import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Consumer  implements  ConsumerSeekAware {
    public static final String TOPIC = "trucks.position";
    private final Logger log = LoggerFactory.getLogger(TruckController.class);

    @Override
    public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
        callback.seekToTimestamp(assignments.keySet(), System.currentTimeMillis()-60*1000*60*60);
    }

    @KafkaListener(id = "hgog-chat", topics = TOPIC)
    public synchronized void received(TruckPosition position, Acknowledgment ack) {
    //    log.info("message received {}", position);
        TruckPositionRepository.addPosition(position);
        ack.acknowledge();
    }
}
