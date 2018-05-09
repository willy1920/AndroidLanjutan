package com.example.rzl.resultactivitymahasiswa;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mahasiswa {
    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    @SerializedName("jk") private String jk;
    @SerializedName("nama") private String nama;
    @SerializedName("nim") private String nim;

    public Mahasiswa(){}
}
