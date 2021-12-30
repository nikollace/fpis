package fon.bg.ac.rs.fpis.trunks.controller;

import fon.bg.ac.rs.fpis.trunks.dto.DobavljacDto;
import fon.bg.ac.rs.fpis.trunks.dto.MaterijalDto;
import fon.bg.ac.rs.fpis.trunks.dto.NarudzbenicaDto;
import fon.bg.ac.rs.fpis.trunks.dto.StavkaNarudzbeniceDto;
import fon.bg.ac.rs.fpis.trunks.model.Dobavljac;
import fon.bg.ac.rs.fpis.trunks.model.Materijal;
import fon.bg.ac.rs.fpis.trunks.model.Narudzbenica;
import fon.bg.ac.rs.fpis.trunks.model.StavkaNarudzbenice;
import fon.bg.ac.rs.fpis.trunks.service.NarudzbenicaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/narudzbenica")
public class NarudzbenicaController {

    public static final Logger logger = LoggerFactory.getLogger(NarudzbenicaController.class);

    private final NarudzbenicaService narudzbenicaService;
    private final ConversionService conversionService;

    @Autowired
    public NarudzbenicaController(NarudzbenicaService narudzbenicaService, ConversionService conversionService) {
        this.narudzbenicaService = narudzbenicaService;
        this.conversionService = conversionService;
    }

    @GetMapping("/dobavljac/{pib}")
    public DobavljacDto vratiDobavljaca(@PathVariable String pib) {
        logger.info("Getting dobavljac...");
        Dobavljac dobavljac = narudzbenicaService.vratiDobavljaca(pib);
        logger.info("Dobavljac: {}", dobavljac);
        return conversionService.convert(dobavljac, DobavljacDto.class);
    }

    @GetMapping("/{sifra}")
    public NarudzbenicaDto vratiNarudzbenicu(@PathVariable Long sifra) {
        logger.info("Getting narudzbenica...");
        Narudzbenica narudzbenica = narudzbenicaService.vratiNarudzbenicu(sifra);
        logger.info("Narudzbenica: {}", narudzbenica);
        return conversionService.convert(narudzbenica, NarudzbenicaDto.class);
    }

    @GetMapping("/materijal")
    public List<MaterijalDto> vratiMaterijale() {
        logger.info("Getting materijali...");
        List<Materijal> materijali = narudzbenicaService.vratiMaterijale();
        return materijali.stream().map(m -> conversionService.convert(m, MaterijalDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/stavka")
    public List<StavkaNarudzbeniceDto> vratiStavke() {
        logger.info("Getting stavke narudzbenice...");
        List<StavkaNarudzbenice> stavke = narudzbenicaService.vratiStavke();
        return stavke.stream().map(s -> conversionService.convert(s, StavkaNarudzbeniceDto.class)).collect(Collectors.toList());
    }
}
