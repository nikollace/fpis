package fon.bg.ac.rs.fpis.trunks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fon.bg.ac.rs.fpis.trunks.model.AdresaDobavljaca;

import java.util.Objects;

public class DobavljacDto {

    @JsonProperty("naziv")
    private String naziv;

    @JsonProperty("pib")
    private String pib;

    @JsonProperty("telefon")
    private String telefon;

    @JsonProperty("adresa_dobavljaca")
    private AdresaDobavljaca adresaDobavljaca;

    public DobavljacDto() {
    }

    public DobavljacDto(String naziv, String pib, String telefon, AdresaDobavljaca adresaDobavljaca) {
        this.naziv = naziv;
        this.pib = pib;
        this.telefon = telefon;
        this.adresaDobavljaca = adresaDobavljaca;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public AdresaDobavljaca getAdresaDobavljaca() {
        return adresaDobavljaca;
    }

    public void setAdresaDobavljaca(AdresaDobavljaca adresaDobavljaca) {
        this.adresaDobavljaca = adresaDobavljaca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DobavljacDto that = (DobavljacDto) o;
        return Objects.equals(naziv, that.naziv) && Objects.equals(pib, that.pib) && Objects.equals(telefon, that.telefon) && Objects.equals(adresaDobavljaca, that.adresaDobavljaca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, pib, telefon, adresaDobavljaca);
    }

    @Override
    public String toString() {
        return "DobavljacDto{" +
                "naziv='" + naziv + '\'' +
                ", pib='" + pib + '\'' +
                ", telefon='" + telefon + '\'' +
                ", adresaDobavljaca=" + adresaDobavljaca +
                '}';
    }
}
