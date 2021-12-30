package fon.bg.ac.rs.fpis.trunks.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Narudzbenica {

    private Long sifra;
    private Dobavljac dobavljac;
    private Timestamp datum;
    private String napomena;

    public Narudzbenica() {
    }

    public Narudzbenica(Long sifra, Dobavljac dobavljac, Timestamp datum, String napomena) {
        this.sifra = sifra;
        this.dobavljac = dobavljac;
        this.datum = datum;
        this.napomena = napomena;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public Dobavljac getDobavljac() {
        return dobavljac;
    }

    public void setDobavljac(Dobavljac dobavljac) {
        this.dobavljac = dobavljac;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzbenica that = (Narudzbenica) o;
        return Objects.equals(sifra, that.sifra) && Objects.equals(dobavljac, that.dobavljac) && Objects.equals(datum, that.datum) && Objects.equals(napomena, that.napomena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, dobavljac, datum, napomena);
    }

    @Override
    public String toString() {
        return "Narudzbenica{" +
                "sifra=" + sifra +
                ", dobavljac=" + dobavljac +
                ", datum=" + datum +
                ", napomena='" + napomena + '\'' +
                '}';
    }
}
