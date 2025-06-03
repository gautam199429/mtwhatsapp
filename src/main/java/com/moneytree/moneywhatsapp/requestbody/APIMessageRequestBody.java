package com.moneytree.moneywhatsapp.requestbody;

import java.util.List;

import com.moneytree.moneywhatsapp.enums.MessageType;

public class APIMessageRequestBody {

    private List<String> phoneNumbers;
    private MessageType type;
    private String message;
    private String link;
    private String filename;
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
    public MessageType getType() {
        return type;
    }
    public void setType(MessageType type) {
        this.type = type;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Getters and Setters

    
}
