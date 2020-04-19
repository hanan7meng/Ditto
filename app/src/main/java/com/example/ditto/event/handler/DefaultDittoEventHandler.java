package com.example.ditto.event.handler;

import androidx.core.util.Consumer;

import com.example.ditto.event.DittoEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * 默认为EventBus实现
 *
 * @author menghaonan
 * @date 2020/04/19
 */
public class DefaultDittoEventHandler implements IDittoEventHandler {

    @Override
    public void register(Consumer consumer) {
        EventBus.getDefault().register(consumer);
    }

    @Override
    public void unRegister(Consumer consumer) {
        EventBus.getDefault().unregister(consumer);
    }

    @Override
    public void sendEvent(DittoEvent event) {
        EventBus.getDefault().post(event);
    }
}
