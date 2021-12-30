package fon.bg.ac.rs.fpis.trunks.model;

import java.util.Objects;

public class AdresaDobavljaca {

    private String ulica;
    private String broj;
    private String grad;
    private String zemlja;

    public AdresaDobavljaca() {
    }

    public AdresaDobavljaca(String ulica, String broj, String grad, String zemlja) {
        this.ulica = ulica;
        this.broj = broj;
        this.grad = grad;
        this.zemlja = zemlja;
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

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresaDobavljaca that = (AdresaDobavljaca) o;
        return Objects.equals(ulica, that.ulica) && Objects.equals(broj, that.broj) && Objects.equals(grad, that.grad) && Objects.equals(zemlja, that.zemlja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ulica, broj, grad, zemlja);
    }

    @Override
    public String toString() {
        return "AdresaDobavljaca{" +
                "ulica='" + ulica + '\'' +
                ", broj='" + broj + '\'' +
                ", grad='" + grad + '\'' +
                ", zemlja='" + zemlja + '\'' +
                '}';
    }
}
