package com.example.grantme2;

public class DataClass {
    public String imageURL;
    public String namaBeasiswa;
    public String sisaWaktu;

    public DataClass(String imageURL, String caption) {

    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getNamaBeasiswa() {
        return namaBeasiswa;
    }

    public void setNamaBeasiswa(String namaBeasiswa) {
        this.namaBeasiswa = namaBeasiswa;
    }

    public String getSisaWaktu() {
        return sisaWaktu;
    }

    public void setSisaWaktu(String sisaWaktu) {
        this.sisaWaktu = sisaWaktu;
    }


    public DataClass(String imageURL, String namaBeasiswa, String sisaWaktu) {
        this.imageURL = imageURL;
        this.namaBeasiswa = namaBeasiswa;
        this.sisaWaktu = sisaWaktu;
    }

    public String getCaption() {
        return namaBeasiswa;
    }
}
