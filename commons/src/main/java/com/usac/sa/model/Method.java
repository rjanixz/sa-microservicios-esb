package com.usac.sa.uber.beans;

public class Method {

    enum TYPE {
        GET,
        POST
        // TODO add others
    }

    private TYPE type;
    private String path;
}
