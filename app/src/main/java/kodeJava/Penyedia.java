package kodeJava;

public class Penyedia {
    String namaInstansi, emailInstansi, noTelpInstansi;
    public Penyedia(){}

    public Penyedia(String namaInstansi, String emailInstansi, String noTelpInstansi) {
        this.namaInstansi = namaInstansi;
        this.emailInstansi = emailInstansi;
        this.noTelpInstansi = noTelpInstansi;
    }


    public String getNamaInstansi() {
        return namaInstansi;
    }

    public void setNamaInstansi(String namaInstansi) {
        this.namaInstansi = namaInstansi;
    }

    public String getEmailInstansi() {
        return emailInstansi;
    }

    public void setEmailInstansi(String emailInstansi) {
        this.emailInstansi = emailInstansi;
    }

    public String getNoTelpInstansi() {
        return noTelpInstansi;
    }

    public void setNoTelpInstansi(String noTelpInstansi) {
        this.noTelpInstansi = noTelpInstansi;
    }
}
