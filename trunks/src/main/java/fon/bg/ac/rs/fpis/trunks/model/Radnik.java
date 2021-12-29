package fon.bg.ac.rs.fpis.trunks.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Radnik {

    private Long sifra;
    private String jmbg;
    private String imePrezime;
    private Double koeficijent;
    private Pozicija pozicija;
    private Status status;
    private Timestamp datumZaposlenja;

    public Radnik() {
    }

    public Radnik(String jmbg, String imePrezime, Double koeficijent, Pozicija pozicija, Status status, Timestamp timestamp) {
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
        this.koeficijent = koeficijent;
        this.pozicija = pozicija;
        this.status = status;
        this.datumZaposlenja = timestamp;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public Double getKoeficijent() {
        return koeficijent;
    }

    public void setKoeficijent(Double koeficijent) {
        this.koeficijent = koeficijent;
    }

    public Pozicija getPozicija() {
        return pozicija;
    }

    public void setPozicija(Pozicija pozicija) {
        this.pozicija = pozicija;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Timestamp datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Radnik radnik = (Radnik) o;
        return Objects.equals(sifra, radnik.sifra) && Objects.equals(jmbg, radnik.jmbg) && Objects.equals(imePrezime, radnik.imePrezime) && Objects.equals(koeficijent, radnik.koeficijent) && pozicija == radnik.pozicija && status == radnik.status && Objects.equals(datumZaposlenja, radnik.datumZaposlenja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, jmbg, imePrezime, koeficijent, pozicija, status, datumZaposlenja);
    }

    @Override
    public String toString() {
        return "Radnik{" +
                "sifra=" + sifra +
                ", jmbg='" + jmbg + '\'' +
                ", imePrezime='" + imePrezime + '\'' +
                ", koeficijent=" + koeficijent +
                ", pozicija=" + pozicija +
                ", status=" + status +
                ", datumZaposlenja=" + datumZaposlenja +
                '}';
    }
}
