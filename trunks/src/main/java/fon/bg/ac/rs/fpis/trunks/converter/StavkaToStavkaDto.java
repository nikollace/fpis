package fon.bg.ac.rs.fpis.trunks.converter;

import fon.bg.ac.rs.fpis.trunks.dto.StavkaNarudzbeniceDto;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import org.springframework.core.convert.converter.Converter;

public class StavkaToStavkaDto implements Converter<StavkaNarudzbenice, StavkaNarudzbeniceDto> {

    @Override
    public StavkaNarudzbeniceDto convert(StavkaNarudzbenice source) {
        return new StavkaNarudzbeniceDto(source.getRedni_broj(), source.getNarudzbenica().getSifra(), source.getKolicina(), source.getJm(), source.getMaterijal());
    }
}
