package com.heart.springcloud.service.impl;

import com.heart.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;

/**
 * @ClassName: IMessageProviderImpl
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/29 16:52
 * @Version: v1.0
 */
@EnableBinding(Source.class)//定义消息的推送管道
@Slf4j
public class IMessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send(Message message) {
        boolean send = output.send(message);
        Object payload = message.getPayload();
        log.info("SEND MESSAGE PAYLOAD :{}", payload);
        return send ? "success" : "fail";
    }
}
