package fon.bg.ac.rs.fpis.trunks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class MaterijalDto {

    @JsonProperty("sifra")
    private Long sifra;

    @JsonProperty("naziv")
    private String naziv;

    public MaterijalDto() {
    }

    public MaterijalDto(Long sifra, String naziv) {
        this.sifra = sifra;
        this.naziv = naziv;
    }

    public Long getSifra() {
        return sifra;
    }

    public void setSifra(Long sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterijalDto that = (MaterijalDto) o;
        return Objects.equals(sifra, that.sifra) && Objects.equals(naziv, that.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, naziv);
    }

    @Override
    public String toString() {
        return "MaterijalDto{" +
                "sifra=" + sifra +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
