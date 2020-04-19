package com.example.ditto.event.handler;

import androidx.core.util.Consumer;

import com.example.ditto.event.DittoEvent;

/**
 * 处理DittoEvent的注册、解注册、发送
 *
 * @author menghaonan
 * @date 2020/04/19
 */
public interface IDittoEventHandler {

    void register(Consumer consumer);

    void unRegister(Consumer consumer);

    void sendEvent(DittoEvent event);

}
