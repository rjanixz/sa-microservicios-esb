package com.usac.sa.model;

import java.util.ArrayList;
import java.util.List;

public class Service {

    private String id;
    private String name;
    private String description;
    private String rootPath;
    private String host;
    private int port;

    List<Method> methods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Method> getMethods() {
        if (methods == null) {
            this.methods =  new ArrayList<>();
        }
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Service) {
            return this.id.equals(((Service) obj).id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
