package com.github.zeldigas.spring.env;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.typesafe.config.*;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.origin.Origin;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.boot.origin.TextResourceOrigin;
import org.springframework.boot.origin.TextResourceOrigin.Location;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * Strategy to load '.conf' files in
 * <a href="https://github.com/typesafehub/config/blob/master/HOCON.md">HOCON</a>
 * format into a {@link PropertySource}.
 */
public class HoconPropertySourceLoader implements PropertySourceLoader {

    private static final ConfigParseOptions PARSE_OPTIONS =
        ConfigParseOptions.defaults().setSyntax(ConfigSyntax.CONF);

    @Override
    public String[] getFileExtensions() {
        return new String[] {"conf"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Map<String, Object> source = toFlatMap(resource, parseHoconFrom(resource));
        return Collections.singletonList(
            new OriginTrackedMapPropertySource(name, source)
        );
    }

    private Config parseHoconFrom(Resource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            return ConfigFactory.parseReader(reader, PARSE_OPTIONS).resolve();
        }
    }

    private Map<String, Object> toFlatMap(Resource resource, Config config) {
        Map<String, Object> properties = new LinkedHashMap<>();
        toFlatMap(properties, "", resource, config);
        return properties;
    }

    private void toFlatMap(Map<String, Object> properties, String parentKey, Resource resource, Config config) {
        final String prefix = "".equals(parentKey) ? "" : parentKey + ".";

        for (Map.Entry<String, ConfigValue> entry : config.entrySet()) {
            String propertyKey = prefix + entry.getKey();
            addConfigValuePropertyTo(properties, propertyKey, resource, entry.getValue());
        }
    }

    private void addConfigValuePropertyTo(Map<String, Object> properties, String key, Resource resource, ConfigValue value) {
        if (value instanceof ConfigList) {
            processListValue(properties, key, resource, (ConfigList) value);
        } else if (value instanceof ConfigObject) {
            processObjectValue(properties, key, resource, (ConfigObject) value);
        } else {
            processScalarValue(properties, key, resource, value);
        }
    }

    private void processListValue(Map<String, Object> properties, String key,
        final Resource resource, ConfigList value) {
        for (int i = 0; i < value.size(); i++) {
            // Used to properly populate lists in @ConfigurationProperties beans
            String propertyName = String.format("%s[%d]", key, i);
            ConfigValue propertyValue = value.get(i);
            addConfigValuePropertyTo(properties, propertyName, resource, propertyValue);
        }
    }

    private void processObjectValue(Map<String, Object> properties, String key, Resource resource, ConfigObject value) {
        toFlatMap(properties, key, resource, value.toConfig());
    }

    private void processScalarValue(Map<String, Object> properties, String key, Resource resource, ConfigValue value) {
        properties.put(key, value.unwrapped());
        Origin origin = new TextResourceOrigin(resource, new Location(value.origin().lineNumber() - 1, 0));
        properties.put(key, OriginTrackedValue.of(value.unwrapped(), origin));
    }
}
