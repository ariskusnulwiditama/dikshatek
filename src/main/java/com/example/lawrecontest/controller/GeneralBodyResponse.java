package com.example.lawrecontest.controller;

import java.io.Serializable;

public class GeneralBodyResponse implements Serializable {

    private int code;
    private String status;
    private String message;
    private Object data;

    public GeneralBodyResponse(int code, String status, String message, Object data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
