package com.github.zeldigas.example.hocon;

import java.util.List;
import java.util.Map;

public class SubConfiguration {

    private List<String> endpoints;

    private Map<String,Object> connectionSettings;

    public List<String> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

    public Map<String, Object> getConnectionSettings() {
        return connectionSettings;
    }

    public void setConnectionSettings(Map<String, Object> connectionSettings) {
        this.connectionSettings = connectionSettings;
    }
}
