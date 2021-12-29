package fon.bg.ac.rs.fpis.trunks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fon.bg.ac.rs.fpis.trunks.model.Pozicija;
import fon.bg.ac.rs.fpis.trunks.model.Status;

import java.sql.Timestamp;
import java.util.Objects;

public class RadnikDto {

    @JsonProperty("sifra")
    private Long sifra;

    @JsonProperty("jmbg")
    private String jmbg;

    @JsonProperty("ime_prezime")
    private String imePrezime;

    @JsonProperty("koeficijent")
    private Double koeficijent;

    @JsonProperty("pozicija")
    private Pozicija pozicija;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("datum_zaposlenja")
    private Timestamp datumZaposlenja;

    public RadnikDto() {
    }

    public RadnikDto(Long sifra, String jmbg, String imePrezime, Double koeficijent, Pozicija pozicija, Status status, Timestamp datumZaposlenja) {
        this.sifra = sifra;
        this.jmbg = jmbg;
        this.imePrezime = imePrezime;
        this.koeficijent = koeficijent;
        this.pozicija = pozicija;
        this.status = status;
        this.datumZaposlenja = datumZaposlenja;
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
        RadnikDto radnikDto = (RadnikDto) o;
        return Objects.equals(sifra, radnikDto.sifra) && Objects.equals(jmbg, radnikDto.jmbg) && Objects.equals(imePrezime, radnikDto.imePrezime) && Objects.equals(koeficijent, radnikDto.koeficijent) && pozicija == radnikDto.pozicija && status == radnikDto.status && Objects.equals(datumZaposlenja, radnikDto.datumZaposlenja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, jmbg, imePrezime, koeficijent, pozicija, status, datumZaposlenja);
    }

    @Override
    public String toString() {
        return "RadnikDto{" +
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
