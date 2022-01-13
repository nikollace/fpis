package fon.bg.ac.rs.fpis.trunks.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AdresaID implements Serializable {

    private String ulica;
    private String broj;
    private String grad;

    public AdresaID() {
    }

    public AdresaID(String ulica, String broj, String grad) {
        this.ulica = ulica;
        this.broj = broj;
        this.grad = grad;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresaID adresaID = (AdresaID) o;
        return Objects.equals(ulica, adresaID.ulica) && Objects.equals(broj, adresaID.broj) && Objects.equals(grad, adresaID.grad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ulica, broj, grad);
    }

    @Override
    public String toString() {
        return "AdresaID{" +
                "ulica='" + ulica + '\'' +
                ", broj='" + broj + '\'' +
                ", grad='" + grad + '\'' +
                '}';
    }
}
