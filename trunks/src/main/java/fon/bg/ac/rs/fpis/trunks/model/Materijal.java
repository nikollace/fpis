package fon.bg.ac.rs.fpis.trunks.model;

import java.util.Objects;

public class Materijal {

    private Long sifra;
    private String naziv;

    public Materijal() {
    }

    public Materijal(Long sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Materijal materijal = (Materijal) o;
        return Objects.equals(sifra, materijal.sifra) && Objects.equals(naziv, materijal.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, naziv);
    }

    @Override
    public String toString() {
        return "Materijal{" +
                "sifra=" + sifra +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
