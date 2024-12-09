package com.savespendr.backend.user_management_service.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private String message;
    private Boolean error;
    private Object data;

    public BaseResponse(String message, Boolean error, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
