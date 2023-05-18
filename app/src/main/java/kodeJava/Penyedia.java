package kodeJava;

public class Penyedia {
    String namaIns, emailIns, noTelpIns;

    public String getNamaIns() {
        return namaIns;
    }

    public void setNamaIns(String namaIns) {
        this.namaIns = namaIns;
    }

    public String getEmailIns() {
        return emailIns;
    }

    public void setEmailIns(String emailIns) {
        this.emailIns = emailIns;
    }

    public String getNoTelpIns() {
        return noTelpIns;
    }

    public void setNoTelpIns(String noTelpIns) {
        this.noTelpIns = noTelpIns;
    }

    public Penyedia(){}
    public Penyedia(String namaIns, String emailIns, String noTelpIns) {
        this.namaIns = namaIns;
        this.emailIns = emailIns;
        this.noTelpIns = noTelpIns;
    }
}
