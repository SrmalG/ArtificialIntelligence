package com.example.demo.dto;

import java.util.ArrayList;

public class GenericResponse {

    private boolean success;
    private String message;
    private ArrayList<?> arraylist;

    public GenericResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public GenericResponse(boolean success, String message, ArrayList<?> arraylist) {
        this.success = success;
        this.message = message;
        this.arraylist = arraylist;
    }

    public ArrayList<?> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<?> arraylist) {
        this.arraylist = arraylist;
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
