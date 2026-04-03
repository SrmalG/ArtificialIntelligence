package com.example.demo.dto;

public class GenericResponse {

    private boolean success;
    private String message;

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return "GenericErrorResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
