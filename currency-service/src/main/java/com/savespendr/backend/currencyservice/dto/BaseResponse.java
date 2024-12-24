package com.savespendr.backend.currencyservice.dto;

public class BaseResponse<T> {
    private String message;
    private Boolean error;
    private T data;

    public BaseResponse() {}

    public BaseResponse(String message, Boolean error, T data) {
        this.message = message;
        this.error = error;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
