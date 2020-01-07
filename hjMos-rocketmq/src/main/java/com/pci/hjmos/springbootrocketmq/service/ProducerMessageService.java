package com.pci.hjmos.springbootrocketmq.service;

import com.pci.hjmos.springbootrocketmq.entity.ProduceMessage;

public interface ProducerMessageService {

    /**
     * 生产消息的唯一对外服务入口
     * @param produceMessage
     * @return
     */
    boolean produceMessage(ProduceMessage produceMessage);


}
