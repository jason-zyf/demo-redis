package com.pci.hjmos.springbootkafka.service.impl;

import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;
import com.pci.hjmos.springbootkafka.service.KafkaProduceMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class KafkaProduceMessageServiceImpl implements KafkaProduceMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean produceMessage(KafkaProduceMessage produceMessage) {
        ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(produceMessage.getTopic(), produceMessage.getContent());
        try {
            SendResult<String, String> SendResult = send.get();
            log.info("发送消息成功，"+SendResult.toString());
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return true;
    }
}
