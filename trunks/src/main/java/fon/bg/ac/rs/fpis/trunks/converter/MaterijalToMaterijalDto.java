package fon.bg.ac.rs.fpis.trunks.converter;

import fon.bg.ac.rs.fpis.trunks.dto.MaterijalDto;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import org.springframework.core.convert.converter.Converter;

public class MaterijalToMaterijalDto implements Converter<Materijal, MaterijalDto> {

    @Override
    public MaterijalDto convert(Materijal source) {
        return new MaterijalDto(source.getSifra(), source.getNaziv());
    }
}
