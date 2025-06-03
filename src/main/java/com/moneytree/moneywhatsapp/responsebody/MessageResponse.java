package com.moneytree.moneywhatsapp.responsebody;

import java.util.List;

public class MessageResponse {
    private String messaging_product;
    private List<Contact> contacts;
    private List<Message> messages;
    private ErrorResponse error;

    public MessageResponse() {}

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public static class Contact {
        private String input;
        private String wa_id;

        public Contact() {}

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getWa_id() {
            return wa_id;
        }

        public void setWa_id(String wa_id) {
            this.wa_id = wa_id;
        }
    }

    public static class Message {
        private String id;

        public Message() {}

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}