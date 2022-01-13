package fon.bg.ac.rs.fpis.trunks.model;

import java.io.Serializable;
import java.util.Objects;

public class StavkaID implements Serializable {

    private Long redni_broj;
    private Long narudzbenica;

    public StavkaID() {
    }

    public StavkaID(Long redni_broj, Long narudzbenica) {
        this.redni_broj = redni_broj;
        this.narudzbenica = narudzbenica;
    }

    public Long getRedni_broj() {
        return redni_broj;
    }

    public void setRedni_broj(Long redni_broj) {
        this.redni_broj = redni_broj;
    }

    public Long getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Long narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaID stavkaID = (StavkaID) o;
        return Objects.equals(redni_broj, stavkaID.redni_broj) && Objects.equals(narudzbenica, stavkaID.narudzbenica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redni_broj, narudzbenica);
    }

    @Override
    public String toString() {
        return "StavkaID{" +
                "redni_broj=" + redni_broj +
                ", narudzbenica=" + narudzbenica +
                '}';
    }
}
