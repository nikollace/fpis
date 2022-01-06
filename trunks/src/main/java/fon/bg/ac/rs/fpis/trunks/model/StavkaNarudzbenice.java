package fon.bg.ac.rs.fpis.trunks.model;

import java.util.Objects;

public class StavkaNarudzbenice {

    private Long redni_broj;
    private Narudzbenica narudzbenica;
    private Integer kolicina;
    private String jm;
    private Materijal materijal;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Long redniBroj, Narudzbenica narudzbenica, Integer kolicina, String jm, Materijal materijal) {
        this.redni_broj = redniBroj;
        this.narudzbenica = narudzbenica;
        this.kolicina = kolicina;
        this.jm = jm;
        this.materijal = materijal;
    }

    public Long getRedni_broj() {
        return redni_broj;
    }

    public void setRedni_broj(Long redni_broj) {
        this.redni_broj = redni_broj;
    }

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getJm() {
        return jm;
    }

    public void setJm(String jm) {
        this.jm = jm;
    }

    public Materijal getMaterijal() {
        return materijal;
    }

    public void setMaterijal(Materijal materijal) {
        this.materijal = materijal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbenice that = (StavkaNarudzbenice) o;
        return Objects.equals(redni_broj, that.redni_broj) && Objects.equals(narudzbenica, that.narudzbenica) && Objects.equals(kolicina, that.kolicina) && Objects.equals(jm, that.jm) && Objects.equals(materijal, that.materijal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redni_broj, narudzbenica, kolicina, jm, materijal);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbenice{" +
                "redniBroj=" + redni_broj +
                ", narudzbenica=" + narudzbenica +
                ", kolicina=" + kolicina +
                ", jm='" + jm + '\'' +
                ", materijal=" + materijal +
                '}';
    }
}
