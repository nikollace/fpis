package fon.bg.ac.rs.fpis.trunks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "narudzbenica")
public class Narudzbenica implements Serializable {

    @Id
    private Long sifra;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dobavljac", referencedColumnName = "pib")
    private Dobavljac dobavljac;
    private Timestamp datum;
    private String napomena;

    @OneToMany(mappedBy = "narudzbenica", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private List<StavkaNarudzbenice> stavke;

    public Narudzbenica() {
    }

    public Narudzbenica(Long sifra) {
        this.sifra = sifra;
    }

    public Narudzbenica(Long sifra, Dobavljac dobavljac, Timestamp datum, String napomena) {
        this.sifra = sifra;
        this.dobavljac = dobavljac;
        this.datum = datum;
        this.napomena = napomena;
    }

    public Narudzbenica(Long sifra, Dobavljac dobavljac, Timestamp datum, String napomena, List<StavkaNarudzbenice> stavke) {
        this.sifra = sifra;
        this.dobavljac = dobavljac;
        this.datum = datum;
        this.napomena = napomena;
        this.stavke = stavke;
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

    public List<StavkaNarudzbenice> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaNarudzbenice> stavke) {
        this.stavke = stavke;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzbenica that = (Narudzbenica) o;
        return Objects.equals(sifra, that.sifra) && Objects.equals(dobavljac, that.dobavljac) && Objects.equals(datum, that.datum) && Objects.equals(napomena, that.napomena) && Objects.equals(stavke, that.stavke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, dobavljac, datum, napomena, stavke);
    }

    @Override
    public String toString() {
        return "Narudzbenica{" +
                "sifra=" + sifra +
                ", dobavljac=" + dobavljac +
                ", datum=" + datum +
                ", napomena='" + napomena + '\'' +
                ", stavke=" + stavke +
                '}';
    }
}
