package com.github.zeldigas.example.hocon;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationPropertiesBinding
public class StringToLocaleConverter implements Converter<String, Locale> {

    @Override
    public Locale convert(String s) {
        return Locale.forLanguageTag(s);

    }
}
