package fon.bg.ac.rs.fpis.trunks.controller;

import fon.bg.ac.rs.fpis.trunks.dto.RadnikDto;
import fon.bg.ac.rs.fpis.trunks.exceptions.RadnikNotFoundException;
import fon.bg.ac.rs.fpis.trunks.model.Radnik;
import fon.bg.ac.rs.fpis.trunks.service.RadnikService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/radnik")
public class RadnikController {
    public static final Logger logger = LoggerFactory.getLogger(RadnikController.class);

    private final RadnikService radnikService;
    private final ConversionService conversionService;

    @Autowired
    public RadnikController(RadnikService radnikService, ConversionService conversionService) {
        this.radnikService = radnikService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public Boolean insert(@RequestBody RadnikDto radnikDto) {
        logger.info("Inserting radnik...");
        Boolean insert = radnikService.insert(radnikDto);
        logger.info("Radnik inserted.");
        return insert;
    }

    @PatchMapping("/{id}")
    public Boolean update(@PathVariable Long id, @RequestBody RadnikDto radnikDto) {
        logger.info("Updating radnik...");
        Boolean update = radnikService.update(id, radnikDto);
        logger.info("Radnik updated.");
        return update;
    }

    @GetMapping("/{id}")
    public RadnikDto get(@PathVariable Long id) {
        logger.info("Getting radnik...");
        Radnik radnik = radnikService.get(id);
        if(radnik == null) {
            return null;
        }
        logger.info("Radnik: {}", radnik);
        return conversionService.convert(radnik, RadnikDto.class);
    }
}
