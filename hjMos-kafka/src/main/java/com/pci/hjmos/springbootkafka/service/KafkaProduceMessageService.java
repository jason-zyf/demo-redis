package com.pci.hjmos.springbootkafka.service;

import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;

public interface KafkaProduceMessageService {

    /**
     * 生产消息的唯一对外服务入口
     * @param produceMessage
     * @return
     */
    boolean produceMessage(KafkaProduceMessage produceMessage);

}
