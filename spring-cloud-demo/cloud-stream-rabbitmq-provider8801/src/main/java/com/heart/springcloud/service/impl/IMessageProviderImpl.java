package com.heart.springcloud.service.impl;

import com.heart.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send(Message message) {
        boolean send = output.send(message);
        Object payload = message.getPayload();
        log.info("MQ MESSAGE OUTPUT IN SERVER PORT [{}], payload :{}", serverPort, payload);
        return send ? "success" : "fail";
    }
}
