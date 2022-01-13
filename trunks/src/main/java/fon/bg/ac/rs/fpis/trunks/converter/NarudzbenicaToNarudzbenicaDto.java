package fon.bg.ac.rs.fpis.trunks.converter;

import fon.bg.ac.rs.fpis.trunks.dto.NarudzbenicaDto;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import org.springframework.core.convert.converter.Converter;

public class NarudzbenicaToNarudzbenicaDto implements Converter<Narudzbenica, NarudzbenicaDto> {

    @Override
    public NarudzbenicaDto convert(Narudzbenica source) {
        return new NarudzbenicaDto(source.getSifra(), source.getDobavljac(), source.getDatum(), source.getNapomena(), source.getStavke());
    }
}
