package fon.bg.ac.rs.fpis.trunks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class NarudzbenicaDto {

    @JsonProperty("sifra")
    private Long sifra;

    @JsonProperty("dobavljac")
    private Dobavljac dobavljac;

    @JsonProperty("datum")
    private Timestamp datum;

    @JsonProperty("napomena")
    private String napomena;

    @JsonProperty("stavke")
    private List<StavkaNarudzbenice> stavke;

    public NarudzbenicaDto() {
    }

    public NarudzbenicaDto(Long sifra, Dobavljac dobavljac, Timestamp datum, String napomena) {
        this.sifra = sifra;
        this.dobavljac = dobavljac;
        this.datum = datum;
        this.napomena = napomena;
    }

    public NarudzbenicaDto(Long sifra, Dobavljac dobavljac, Timestamp datum, String napomena, List<StavkaNarudzbenice> stavke) {
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
        NarudzbenicaDto that = (NarudzbenicaDto) o;
        return Objects.equals(sifra, that.sifra) && Objects.equals(dobavljac, that.dobavljac) && Objects.equals(datum, that.datum) && Objects.equals(napomena, that.napomena) && Objects.equals(stavke, that.stavke);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, dobavljac, datum, napomena, stavke);
    }

    @Override
    public String toString() {
        return "NarudzbenicaDto{" +
                "sifra=" + sifra +
                ", dobavljac=" + dobavljac +
                ", datum=" + datum +
                ", napomena='" + napomena + '\'' +
                ", stavke=" + stavke +
                '}';
    }
}
