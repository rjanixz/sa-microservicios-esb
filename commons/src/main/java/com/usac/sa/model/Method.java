package com.usac.sa.esb.beans;

public class Method {

    enum TYPE {
        GET,
        POST
        // TODO add others
    }

    private TYPE type;
    private String path;
}
