package com.example.grantme2;

public class Penerima {
    String namaLengkap, email, noTelepon, ttl, jenKel;



    public Penerima(){
    }
    public Penerima(String namaLengkap, String email, String noTelepon, String ttl, String jenKel) {
        this.namaLengkap = namaLengkap;
        this.email = email;
        this.noTelepon = noTelepon;
        this.ttl = ttl;
        this.jenKel = jenKel;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }


}
