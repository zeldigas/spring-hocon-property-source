package com.github.zeldigas.spring.env;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Strategy to load '.conf' files in
 * <a href="https://github.com/typesafehub/config/blob/master/HOCON.md">HOCON</a>
 * format into a {@link PropertySource}.
 */
public class HoconPropertySourceLoader implements PropertySourceLoader {

    private static final PropertySourceFactory HOCON_PROPERTY_SOURCE_LOADER = new HoconPropertySourceFactory();

    @Override
    public String[] getFileExtensions() {
        return new String[] {"conf"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        EncodedResource encodedResource = new EncodedResource(resource);
        PropertySource<?> propertySource = HOCON_PROPERTY_SOURCE_LOADER.createPropertySource(name, encodedResource);
        return Collections.singletonList(propertySource);
    }
}
