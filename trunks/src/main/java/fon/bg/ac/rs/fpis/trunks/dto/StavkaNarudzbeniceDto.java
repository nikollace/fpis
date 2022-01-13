package fon.bg.ac.rs.fpis.trunks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;

import java.util.Objects;

public class StavkaNarudzbeniceDto {

    @JsonProperty("redni_broj")
    private Long redniBroj;

    @JsonProperty("narudzbenica_sifra")
    private Long sifraNarudzbenice;

    @JsonProperty("kolicina")
    private Integer kolicina;

    @JsonProperty("jm")
    private String jm;

    @JsonProperty("materijal")
    private Materijal materijal;

    public StavkaNarudzbeniceDto() {
    }

    public StavkaNarudzbeniceDto(Long redniBroj, Long sifraNarudzbenice, Integer kolicina, String jm, Materijal materijal) {
        this.redniBroj = redniBroj;
        this.sifraNarudzbenice = sifraNarudzbenice;
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

    public Long getSifraNarudzbenice() {
        return sifraNarudzbenice;
    }

    public void setSifraNarudzbenice(Long sifraNarudzbenice) {
        this.sifraNarudzbenice = sifraNarudzbenice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbeniceDto that = (StavkaNarudzbeniceDto) o;
        return Objects.equals(redniBroj, that.redniBroj) && Objects.equals(sifraNarudzbenice, that.sifraNarudzbenice) && Objects.equals(kolicina, that.kolicina) && Objects.equals(jm, that.jm) && Objects.equals(materijal, that.materijal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redniBroj, sifraNarudzbenice, kolicina, jm, materijal);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbeniceDto{" +
                "redniBroj=" + redniBroj +
                ", sifraNarudzbenice=" + sifraNarudzbenice +
                ", kolicina=" + kolicina +
                ", jm='" + jm + '\'' +
                ", materijal=" + materijal +
                '}';
    }
}
