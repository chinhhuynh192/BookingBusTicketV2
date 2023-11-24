package com.busticketbooking.busticketbooking.Utils;

import java.io.Serializable;

public class AttrWrapper<T extends Serializable> {
    public final T value;
    public final long timeoutMs;

    private long createdTime = System.currentTimeMillis();
    private long lastAccess = createdTime;
    public AttrWrapper(T value, long timeoutMs) {
        this.value = value;
        this.timeoutMs = timeoutMs;
    }
    public boolean isValid() {
        long now = System.currentTimeMillis();
        if (now - lastAccess > timeoutMs)
            return false;
        lastAccess = now;
        return true;
    }

    public T getValue() {
        return value;
    }

    public long getTimeoutMs() {
        return timeoutMs;
    }

    public long getLastAccess() {
        return lastAccess;
    }

    public long getCreatedTime() {
        return createdTime;
    }

}