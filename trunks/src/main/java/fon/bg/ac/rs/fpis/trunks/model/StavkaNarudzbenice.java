package fon.bg.ac.rs.fpis.trunks.model;

import fon.bg.ac.rs.fpis.trunks.repository.NarudzbenicaJPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stavka_narudzbenice")
@IdClass(StavkaID.class)
public class StavkaNarudzbenice implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "narudzbenica_sifra", referencedColumnName = "sifra")
    private Narudzbenica narudzbenica;

    @Id
    private Long redni_broj;
    private Integer kolicina;
    private String jm;
    @OneToOne
    @JoinColumn(name = "materijal", referencedColumnName = "sifra")
    private Materijal materijal;

    public StavkaNarudzbenice() {
    }

    public StavkaNarudzbenice(Narudzbenica narudzbenica, Long redni_broj, Integer kolicina, String jm, Materijal materijal) {
        this.narudzbenica = narudzbenica;
        this.redni_broj = redni_broj;
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

    public Narudzbenica getNarudzbenica() {
        return narudzbenica;
    }

    public void setNarudzbenica(Narudzbenica narudzbenica) {
        this.narudzbenica = narudzbenica;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StavkaNarudzbenice that = (StavkaNarudzbenice) o;
        return Objects.equals(narudzbenica, that.narudzbenica) && Objects.equals(redni_broj, that.redni_broj) && Objects.equals(kolicina, that.kolicina) && Objects.equals(jm, that.jm) && Objects.equals(materijal, that.materijal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(narudzbenica, redni_broj, kolicina, jm, materijal);
    }

    @Override
    public String toString() {
        return "StavkaNarudzbenice{" +
                "narudzbenica=" + narudzbenica +
                ", redni_broj=" + redni_broj +
                ", kolicina=" + kolicina +
                ", jm='" + jm + '\'' +
                ", materijal=" + materijal +
                '}';
    }
}
