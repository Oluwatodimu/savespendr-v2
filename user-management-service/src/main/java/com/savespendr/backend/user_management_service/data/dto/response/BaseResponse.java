package com.savespendr.backend.user_management_service.data.dto.response;

import lombok.Data;

@Data
public class BaseResponse {

    private String message;
    private Boolean error;
    private Object data;

    public BaseResponse(String message, Boolean error, Object data) {
        this.message = message;
        this.error = error;
        this.data = data;
    }
}
