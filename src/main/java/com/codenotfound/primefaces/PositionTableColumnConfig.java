package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;

public class PositionTableColumnConfig {

    private String title;
    private Integer order;
    private Integer width;
    private Boolean enabled;
    private String alignment;
    private ColumnType columnType;

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    @XmlElement
    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getWidth() {
        return width;
    }

    @XmlElement
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @XmlElement
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAlignment() {
        return alignment;
    }

    @XmlElement
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    @XmlElement
    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }
}
