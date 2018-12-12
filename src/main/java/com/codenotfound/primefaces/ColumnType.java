package com.codenotfound.primefaces;

public enum ColumnType {

    POSITION("position", "Position"),
    ITEM_NO("itemNo", "Art.No."),
    DESCRIPTION("description", "Description"),
    AMOUNT("amount", "Amount Unit"),
    UNIT_PRICE("unitPrice", "Price"),
    TOTAL_PRICE("totalPrice", "Total price"),
    UNIT("unit", "Unit");

    private String field;
    private String title;

    ColumnType(String field, String title) {
        this.field = field;
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
