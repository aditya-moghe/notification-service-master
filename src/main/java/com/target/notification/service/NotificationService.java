package com.target.notification.service;

import com.target.notification.model.ChannelType;
import com.target.notification.model.Message;
import com.target.notification.service.channel.Channel;
import com.target.notification.service.channel.ChannelFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotificationService {

    private static final Logger LOG = LogManager.getLogger(NotificationService.class);

    @Autowired
    ChannelFactory factory;

    public NotificationService(ChannelFactory factory) {
        this.factory = factory;
    }

    private AtomicInteger notificationId = new AtomicInteger(1);

    /**
     * Notifies channel identified by given channelType with the given message.
     */
    public long notifyAll(Message msg) {
        for(Channel c : factory.getChannels()) {
            msg.setId(notificationId.getAndIncrement());
            c.notify(msg);
            LOG.debug("ID = "+notificationId+", Message sent = "+msg);
        }
        return notificationId.longValue();
    }

    /**
     * Notifies all configured channels(like slack and email) with the given message.
     */
    public long notify(ChannelType channelType, Message msg) {
        msg.setId(notificationId.getAndIncrement());
        factory.get(channelType).notify(msg);
        LOG.debug("ID = "+notificationId+", Message sent = "+msg);
        return notificationId.longValue();
    }
}
