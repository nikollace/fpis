package fon.bg.ac.rs.fpis.trunks.configuration;

import fon.bg.ac.rs.fpis.trunks.converter.RadnikToRadnikDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RadnikToRadnikDto());
    }
}
