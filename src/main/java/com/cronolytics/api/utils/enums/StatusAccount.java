package com.cronolytics.api.utils.enums;

public enum StatusAccount {
    PENDING(0),
    VERIFIED(1),
    DELETED(3);

    private final int status;

    StatusAccount(int value) {
        status = value;
    }

    public int getStatus() {
        return status;
    }
}
