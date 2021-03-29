package com.heart.springcloud.controller;

import com.heart.springcloud.service.IMessageProvider;
import com.heart.springcloud.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @ClassName: SendMessageController
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/29 17:01
 * @Version: v1.0
 */
@RestController
@Slf4j
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        String s = UUID.randomUUID().toString();
        Message message = MessageUtils.messageGenerator(s);

        String send = messageProvider.send(message);
        return "MESSAGE [" + s + "] send " + send.toUpperCase();
    }
}
