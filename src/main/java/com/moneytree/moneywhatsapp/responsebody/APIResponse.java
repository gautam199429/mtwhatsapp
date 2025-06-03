package com.moneytree.moneywhatsapp.responsebody;

import java.time.LocalDateTime;
import java.util.UUID;

public class APIResponse {

    private int statusCode;

    private LocalDateTime requestDateTime;

    private String message;

    private Object data;

    private String uniqueRequestId;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(LocalDateTime requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getUniqueRequestId() {
        return uniqueRequestId;
    }

    public void setUniqueRequestId(String uniqueRequestId) {
        this.uniqueRequestId = uniqueRequestId;
    }

    public APIResponse(String message) {
        this.statusCode = 0;
        this.requestDateTime = LocalDateTime.now();
        this.message = message;
        this.uniqueRequestId = UUID.randomUUID().toString();
    }

    public APIResponse(String message,
                       Object data) {
        this.statusCode = 1;
        this.requestDateTime = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.uniqueRequestId = UUID.randomUUID().toString();
    }
}
