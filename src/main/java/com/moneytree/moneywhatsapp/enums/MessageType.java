package com.moneytree.moneywhatsapp.enums;

public enum MessageType {

    TEXT("text"),
    IMAGE("image"),
    VIDEO("video"),
    AUDIO("audio"),
    DOCUMENT("document"),
    LOCATION("location"),
    CONTACT("contact"),
    STICKER("sticker"),
    INTERACTIVE("interactive");

    private final String type;

    MessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
