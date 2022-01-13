package fon.bg.ac.rs.fpis.trunks.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "adresa")
public class AdresaDobavljaca implements Serializable {

    @EmbeddedId
    private AdresaID adresaID;
    private String zemlja;

    public AdresaDobavljaca() {
    }

    public AdresaDobavljaca(AdresaID adresaID, String zemlja) {
        this.adresaID = adresaID;
        this.zemlja = zemlja;
    }

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }

    public AdresaID getAdresaID() {
        return adresaID;
    }

    public void setAdresaID(AdresaID adresaID) {
        this.adresaID = adresaID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresaDobavljaca that = (AdresaDobavljaca) o;
        return Objects.equals(adresaID, that.adresaID) && Objects.equals(zemlja, that.zemlja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresaID, zemlja);
    }

    @Override
    public String toString() {
        return "AdresaDobavljaca{" +
                "adresaID=" + adresaID +
                ", zemlja='" + zemlja + '\'' +
                '}';
    }
}
