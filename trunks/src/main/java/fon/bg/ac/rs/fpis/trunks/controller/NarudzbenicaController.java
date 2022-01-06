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
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
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

    @GetMapping("/stavka/{id}")
    public List<StavkaNarudzbeniceDto> vratiStavke(@PathVariable Long id) {
        logger.info("Getting stavke narudzbenice...");
        List<StavkaNarudzbenice> stavke = narudzbenicaService.vratiStavke(id);
        return stavke.stream().map(s -> conversionService.convert(s, StavkaNarudzbeniceDto.class)).collect(Collectors.toList());
    }

    @GetMapping("/max")
    public List<Long> dajMax() {
        logger.info("Getting max...");
        List<Long> max = narudzbenicaService.dajMax();
        return max;
    }

    @PostMapping
    public Boolean sacuvajNarudzbenicu(@RequestBody NarudzbenicaDto narudzbenicaDto) {
        logger.info("Inserting narudzbenica...");
        Boolean uspesno = narudzbenicaService.sacuvajNarudzbenicu(narudzbenicaDto);
        if(uspesno == true) {
            logger.info("Successfully saved narudzbenica.");
        } else {
            logger.error("Error saving narudzbenica.");
        }
        return uspesno;
    }

    @PatchMapping("/{id}")
    public Boolean izmeniNarudzbenicu(@RequestBody NarudzbenicaDto narudzbenicaDto, @PathVariable Long id) {
        logger.info("Updating narudzbenica...");
        Boolean uspesno = narudzbenicaService.izmeniNarudzbenicu(narudzbenicaDto, id);
        if(uspesno == true) {
            logger.info("Successfully updated narudzbenica.");
        } else {
            logger.error("Error updating narudzbenica.");
        }
        return uspesno;
    }

    @PostMapping("/stavka/{sifra}")
    public void obrisiStavku(@RequestBody ArrayList<Long> rbr, @PathVariable Long sifra) {
        logger.info("Deleting stavka narudzbenice...");
        narudzbenicaService.obrisiStavku(rbr, sifra);
    }
}
