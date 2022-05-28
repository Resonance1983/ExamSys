package com.example.examsys.Support.Redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

//缓存失效监听
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    //数据失效事件
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 获取到失效的 key，输出提醒
        String expiredKey = message.toString();
        System.out.println(expiredKey);
    }
}