package com.heart.springcloud.service;

import org.springframework.messaging.Message;

/**
 * @ClassName: IMessageProvider
 * @Description: TODO
 * @Author: jayhe
 * @Date: 2021/3/29 16:52
 * @Version: v1.0
 */
public interface IMessageProvider {

    /**
     * 发送消息接口
     *
     * @param message
     * @return
     */
    String send(Message message);
}
