package fon.bg.ac.rs.fpis.trunks.model;

import java.util.Objects;

public class StavkaNarudzbenice {

    private Long redniBroj;
    private Narudzbenica narudzbenica;
    private Integer kolicina;
    private String jm;
    private Materijal materijal;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Long redniBroj, Narudzbenica narudzbenica, Integer kolicina, String jm, Materijal materijal) {
        this.redniBroj = redniBroj;
        this.narudzbenica = narudzbenica;
        this.kolicina = kolicina;
        this.jm = jm;
        this.materijal = materijal;
    }

    public Long getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(Long redniBroj) {
        this.redniBroj = redniBroj;
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
        return Objects.equals(redniBroj, that.redniBroj) && Objects.equals(narudzbenica, that.narudzbenica) && Objects.equals(kolicina, that.kolicina) && Objects.equals(jm, that.jm) && Objects.equals(materijal, that.materijal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redniBroj, narudzbenica, kolicina, jm, materijal);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbenice{" +
                "redniBroj=" + redniBroj +
                ", narudzbenica=" + narudzbenica +
                ", kolicina=" + kolicina +
                ", jm='" + jm + '\'' +
                ", materijal=" + materijal +
                '}';
    }
}
