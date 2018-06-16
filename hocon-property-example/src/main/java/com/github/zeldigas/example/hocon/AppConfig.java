package com.github.zeldigas.example.hocon;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Locale;

@Component
@ConfigurationProperties("my-app")
public class AppConfig {

    private String foo;
    private int bar;
    private URI aUri;
    private Locale targetLocale;
    private SubConfiguration configuration;

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public int getBar() {
        return bar;
    }

    public void setBar(int bar) {
        this.bar = bar;
    }

    public URI getaUri() {
        return aUri;
    }

    public void setaUri(URI aUri) {
        this.aUri = aUri;
    }

    public Locale getTargetLocale() {
        return targetLocale;
    }

    public void setTargetLocale(Locale targetLocale) {
        this.targetLocale = targetLocale;
    }

    public SubConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(SubConfiguration configuration) {
        this.configuration = configuration;
    }
}
