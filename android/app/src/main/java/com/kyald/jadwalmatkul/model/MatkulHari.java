package com.kyald.jadwalmatkul.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MatkulHari {

    @SerializedName("id")
    private String id;
    @SerializedName("hari")
    private String hari;

    public MatkulHari(String id, String hari) {
        this.id = id;
        this.hari = hari;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }
}

