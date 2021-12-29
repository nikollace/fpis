package fon.bg.ac.rs.fpis.trunks.converter;

import fon.bg.ac.rs.fpis.trunks.dto.RadnikDto;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import org.springframework.core.convert.converter.Converter;

public class RadnikToRadnikDto implements Converter<Radnik, RadnikDto> {

    @Override
    public RadnikDto convert(Radnik source) {
        return new RadnikDto(source.getSifra(), source.getJmbg(), source.getImePrezime(), source.getKoeficijent(), source.getPozicija(),
                source.getStatus(), source.getDatumZaposlenja());
    }
}
