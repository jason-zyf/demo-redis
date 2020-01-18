package com.pci.hjmos.springbootkafka.controller;

import com.pci.hjmos.springbootkafka.entity.KafkaProduceMessage;
import com.pci.hjmos.springbootkafka.service.KafkaProduceMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaSendController {

    @Autowired
    private KafkaProduceMessageService kafkaProduceMessageService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/sendKafka")
    public boolean createKafkaMessage(){

        String topic = "pci";
        String content = "hhhhjfds";

        KafkaProduceMessage produceMessage = new KafkaProduceMessage(topic,content);
        return kafkaProduceMessageService.produceMessage(produceMessage);
    }


}
