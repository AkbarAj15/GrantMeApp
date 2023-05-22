package kodeJava;
import java.io.Serializable;

public class Beasiswa implements Serializable {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String imageURL, namaBeasiswa, jenisBeasiswa, tanggalBuka, tanggalTutup, kuota, kriteria;

    public Beasiswa() {
    }

    public Beasiswa(String imageURL, String namaBeasiswa, String jenisBeasiswa,
                    String tanggalBuka, String tanggalTutup, String kuota, String kriteria) {
        this.imageURL = imageURL;
        this.namaBeasiswa = namaBeasiswa;
        this.jenisBeasiswa = jenisBeasiswa;
        this.tanggalBuka = tanggalBuka;
        this.tanggalTutup = tanggalTutup;
        this.kuota = kuota;
        this.kriteria = kriteria;
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

    public String getJenisBeasiswa() {
        return jenisBeasiswa;
    }

    public void setJenisBeasiswa(String jenisBeasiswa) {
        this.jenisBeasiswa = jenisBeasiswa;
    }

    public String getTanggalBuka() {
        return tanggalBuka;
    }

    public void setTanggalBuka(String tanggalBuka) {
        this.tanggalBuka = tanggalBuka;
    }

    public String getTanggalTutup() {
        return tanggalTutup;
    }

    public void setTanggalTutup(String tanggalTutup) {
        this.tanggalTutup = tanggalTutup;
    }

    public String getKuota() {
        return kuota;
    }

    public void setKuota(String kuota) {
        this.kuota = kuota;
    }

    public String getKriteria() {
        return kriteria;
    }

    public void setKriteria(String kriteria) {
        this.kriteria = kriteria;
    }
}


