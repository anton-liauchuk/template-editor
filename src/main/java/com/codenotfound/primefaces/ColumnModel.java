package com.codenotfound.primefaces;

public class ColumnModel {

    private String header;
    private String property;

    public ColumnModel() {
    }

    public ColumnModel(String header, String property) {
        this.header = header;
        this.property = property;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

}
