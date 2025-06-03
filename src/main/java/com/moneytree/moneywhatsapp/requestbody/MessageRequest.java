package com.moneytree.moneywhatsapp.requestbody;

public class MessageRequest {

    private String messaging_product = "whatsapp"; // Default messaging product is WhatsApp
    private String to;
    private String type;
    private String recipient_type = "individual"; // Default recipient type is individual
    private MessageDocument document;
    private MessageDocument image;
    private MessageDocument text;

    
    public String getMessaging_product() {
        return messaging_product;
    }
    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRecipient_type() {
        return recipient_type;
    }
    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }
    public MessageDocument getDocument() {
        return document;
    }
    public void setDocument(MessageDocument document) {
        this.document = document;
    }

    public MessageDocument getText() {
        return text;
    }

    public void setText(MessageDocument text) {
        this.text = text;
    }

    public MessageRequest() {
    }

    public MessageDocument getImage() {
        return image;
    }
    public void setImage(MessageDocument image) {
        this.image = image;
    }
}
