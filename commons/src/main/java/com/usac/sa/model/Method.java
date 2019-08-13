package com.usac.sa.model;

import java.util.HashSet;
import java.util.Set;

public class Method {

    public enum TYPE {
        GET,
        POST
        // TODO add others
    }

    private TYPE type;
    private String path;

    Set<String> parameters;

    public Method() {
        this.parameters = new HashSet<>();
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Set<String> getParameters() {
        return parameters;
    }

    public void setParameters(Set<String> parameters) {
        this.parameters = parameters;
    }
}
