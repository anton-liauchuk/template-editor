package com.codenotfound.primefaces;

public enum ColumnType {

    POSITION("position"),
    ITEM_NO("itemNo"),
    DESCRIPTION("description"),
    AMOUNT("amount"),
    UNIT_PRICE("unitPrice"),
    TOTAL_PRICE("totalPrice"),
    UNIT("unit");

    private String field;

    ColumnType(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
