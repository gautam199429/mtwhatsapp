package com.moneytree.moneywhatsapp.responsebody;

public class ErrorResponse {
    private String message;
    private String type;
    private int code;
    private ErrorData error_data;
    private String fbtrace_id;
    

    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }


    public ErrorData getError_data() {
        return error_data;
    }


    public void setError_data(ErrorData error_data) {
        this.error_data = error_data;
    }


    public String getFbtrace_id() {
        return fbtrace_id;
    }


    public void setFbtrace_id(String fbtrace_id) {
        this.fbtrace_id = fbtrace_id;
    }


    public static class ErrorData {
        private String messaging_product;
        private String details;
        public String getMessaging_product() {
            return messaging_product;
        }
        public void setMessaging_product(String messaging_product) {
            this.messaging_product = messaging_product;
        }
        public String getDetails() {
            return details;
        }
        public void setDetails(String details) {
            this.details = details;
        }
        
    }
}