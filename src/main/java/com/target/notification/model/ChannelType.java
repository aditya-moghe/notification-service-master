package com.target.notification.model;

public enum ChannelType {
    slack, email;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
