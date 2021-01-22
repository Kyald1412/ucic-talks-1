package com.kyald.jadwalmatkul.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatkulHariResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("body")
    private List<MatkulHari> body;

    public MatkulHariResponse(String message, List<MatkulHari> body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MatkulHari> getBody() {
        return body;
    }

    public void setBody(List<MatkulHari> body) {
        this.body = body;
    }
}

