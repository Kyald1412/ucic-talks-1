package com.kyald.jadwalmatkul.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatkulDataResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("body")
    private List<MatkulData> body;

    public MatkulDataResponse(String message, List<MatkulData> body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MatkulData> getBody() {
        return body;
    }

    public void setBody(List<MatkulData> body) {
        this.body = body;
    }
}

