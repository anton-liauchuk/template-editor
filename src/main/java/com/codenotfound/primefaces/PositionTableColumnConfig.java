package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class PositionTableColumnConfig {

    private String title;
    private Integer order;
    private Boolean enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    @XmlElement
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public ColumnType getColumnType() {
        return columnType;
    }

    @XmlElement
    public void setColumnType(ColumnType columnType) {
        this.columnType = columnType;
    }
}
