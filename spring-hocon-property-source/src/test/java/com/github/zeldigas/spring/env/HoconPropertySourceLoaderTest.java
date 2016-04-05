package com.github.zeldigas.spring.env;

import org.junit.Test;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HoconPropertySourceLoaderTest {

    @Test
    public void propertyParseIsEqualToYaml() throws Exception {
        HoconPropertySourceLoader hoconLoader = new HoconPropertySourceLoader();
        YamlPropertySourceLoader yamlLoader = new YamlPropertySourceLoader();

        EnumerablePropertySource hoconParse = loadProperties(hoconLoader, "/application.conf");
        EnumerablePropertySource yamlParse = loadProperties(yamlLoader, "/application.yaml");

        verifyPropertyKeysAreSame(hoconParse, yamlParse);
        verifyPropertyValuesAreSame(hoconParse, yamlParse);
    }

    private void verifyPropertyKeysAreSame(EnumerablePropertySource hoconParse, EnumerablePropertySource yamlParse) {
        String[] hoconProperties = hoconParse.getPropertyNames();
        Arrays.sort(hoconProperties);
        String[] yamlProperties = yamlParse.getPropertyNames();
        Arrays.sort(yamlProperties);

        assertThat(hoconProperties, is(yamlProperties));
    }

    private void verifyPropertyValuesAreSame(EnumerablePropertySource hoconParse, EnumerablePropertySource yamlParse) {
        for (String name : hoconParse.getPropertyNames()) {
            assertThat(hoconParse.getProperty(name), is(yamlParse.getProperty(name)));
        }
    }

    private EnumerablePropertySource loadProperties(PropertySourceLoader hoconLoader, String path) throws IOException {
        return (EnumerablePropertySource) hoconLoader.load("hocon", new ClassPathResource(path), null);
    }
}