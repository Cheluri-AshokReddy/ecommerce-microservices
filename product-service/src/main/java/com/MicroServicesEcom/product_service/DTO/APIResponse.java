package com.MicroServicesEcom.product_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class APIResponse {
    private boolean success;
    private String message;

    public APIResponse( String message,boolean success) {
        this.success = success;
        this.message = message;
    }

    public APIResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

