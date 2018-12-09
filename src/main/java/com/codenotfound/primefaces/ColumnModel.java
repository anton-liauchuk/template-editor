package com.codenotfound.primefaces;

public class ColumnModel {

    private String header;
    private String property;
    private String alignment;

    public ColumnModel() {
    }

    public ColumnModel(String header, String property, String alignment) {
        this.header = header;
        this.property = property;
        this.alignment = alignment;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

    public String getAlignment() {
        return alignment;
    }
}
