package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class PositionTable extends EditableElement {

    private List<PositionTableColumnConfig> columnConfigs;

    public List<PositionTableColumnConfig> getColumnConfigs() {
        return columnConfigs;
    }

    @XmlElementWrapper(name = "columnConfigs")
    @XmlElement(name = "columnConfig")
    public void setColumnConfigs(List<PositionTableColumnConfig> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }
}
