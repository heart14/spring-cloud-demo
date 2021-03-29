package com.heart.springcloud.utils;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @ClassName: MessageUtils
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/29 16:59
 * @Version: v1.0
 */
public class MessageUtils {

    public static Message messageGenerator(String payload) {
        return MessageBuilder.withPayload(payload).build();
    }
}
