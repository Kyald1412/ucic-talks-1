package com.kyald.jadwalmatkul.model;

import com.google.gson.annotations.SerializedName;

public class MatkulData {

    @SerializedName("id")
    private String id;
    @SerializedName("matkul")
    private String matkul;
    @SerializedName("hari")
    private String hari;
    @SerializedName("id_hari")
    private String id_hari;

    public MatkulData(String id, String matkul, String hari, String id_hari) {
        this.id = id;
        this.matkul = matkul;
        this.hari = hari;
        this.id_hari = id_hari;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getId_hari() {
        return id_hari;
    }

    public void setId_hari(String id_hari) {
        this.id_hari = id_hari;
    }
}