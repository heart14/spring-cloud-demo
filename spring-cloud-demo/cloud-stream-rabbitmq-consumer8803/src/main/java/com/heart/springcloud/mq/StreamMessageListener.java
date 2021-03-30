package com.heart.springcloud.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StreamMessageListener
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/30 10:30
 * @Version: v1.0
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class StreamMessageListener {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void messageReceiver(Message<String> message) {
        log.info("MQ MESSAGE INPUT IN SERVER PORT [{}], payload :{}", serverPort, message.getPayload());
    }
}
