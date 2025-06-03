package com.moneytree.moneywhatsapp.requestbody;

public class MessageDocument {

    private String link;
    private String caption;
    private String filename;
    private String text;


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageDocument() {
    }

    public MessageDocument(String link, String caption, String filename, String text) {
        this.link = link;
        this.caption = caption;
        this.filename = filename;
        this.text = text;
    }

}
