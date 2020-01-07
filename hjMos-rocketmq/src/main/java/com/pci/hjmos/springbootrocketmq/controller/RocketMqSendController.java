package com.pci.hjmos.springbootrocketmq.controller;

import com.pci.hjmos.springbootrocketmq.entity.ProduceMessage;
import com.pci.hjmos.springbootrocketmq.service.ProducerMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RocketMqSendController {

    @Autowired
    private ProducerMessageService prodeucerMessageService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/sendMessage")
    public String sendMessage(String content){

        log.info("发送一条消息...........");
        prodeucerMessageService.produceMessage(new ProduceMessage("my-topic","aa",content));
        return "发送一个消息：" + content;
    }



}
