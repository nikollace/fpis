package fon.bg.ac.rs.fpis.trunks.converter;

import fon.bg.ac.rs.fpis.trunks.dto.DobavljacDto;
import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import org.springframework.core.convert.converter.Converter;

public class DobavljacToDobavljacDto implements Converter<Dobavljac, DobavljacDto> {

    @Override
    public DobavljacDto convert(Dobavljac source) {
        return new DobavljacDto(source.getNaziv(), source.getPib(), source.getTelefon(), source.getAdresaDobavljaca());
    }
}
