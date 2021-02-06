package com.target.notification.service.channel;

import com.target.notification.model.Message;
import com.target.notification.model.ChannelType;

public interface Channel {
    default void notify(Message msg) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }

    default boolean supports(ChannelType type) {
        throw new RuntimeException("Notify method is not implemented yet.");
    }
}
