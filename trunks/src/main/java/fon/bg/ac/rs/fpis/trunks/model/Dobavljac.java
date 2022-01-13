package fon.bg.ac.rs.fpis.trunks.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "dobavljac")
public class Dobavljac implements Serializable {

    private String naziv;
    @Id
    private String pib;
    private String telefon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(value = {
            @JoinColumn(name = "ulica", referencedColumnName = "ulica"),
            @JoinColumn(name = "broj", referencedColumnName = "broj"),
            @JoinColumn(name = "grad", referencedColumnName = "grad")
    })
    private AdresaDobavljaca adresaDobavljaca;

    public Dobavljac() {
    }

    public Dobavljac(String naziv, String pib, String telefon, AdresaDobavljaca adresaDobavljaca) {
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
        Dobavljac dobavljac = (Dobavljac) o;
        return Objects.equals(naziv, dobavljac.naziv) && Objects.equals(pib, dobavljac.pib) && Objects.equals(telefon, dobavljac.telefon) && Objects.equals(adresaDobavljaca, dobavljac.adresaDobavljaca);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, pib, telefon, adresaDobavljaca);
    }

    @Override
    public String toString() {
        return "Dobavljac{" +
                "naziv='" + naziv + '\'' +
                ", pib='" + pib + '\'' +
                ", telefon='" + telefon + '\'' +
                ", adresaDobavljaca=" + adresaDobavljaca +
                '}';
    }
}
