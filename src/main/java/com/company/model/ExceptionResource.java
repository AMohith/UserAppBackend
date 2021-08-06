package com.company.model;

public class ExceptionResource {
    private String Message;
    private int status;

    public ExceptionResource(String message, int status) {
        Message = message;
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "\"Message\" = \"" + Message + '\"' +
                ",\"status\" = " + status +
                '}';
    }
}
