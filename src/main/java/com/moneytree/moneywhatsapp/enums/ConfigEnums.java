package com.moneytree.moneywhatsapp.enums;

public enum ConfigEnums {

    ACCESS_TOKEN("Facebook meta access token"),
    WHATSAPP_BUSINESS_ACCOUNT_ID("Whatsapp Business Account ID"),
    WHATSAPP_BUSINESS_ACCOUNT_NAME("Whatsapp Business Account Name"),
    PHONE_NUMBER_ID("Phone Number ID"),
    WHATSAPP_API_URL("WhatsApp API URL"),
    WHATSAPP_API_VERSION("WhatsApp API Version");
    private final String value;
    ConfigEnums(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
