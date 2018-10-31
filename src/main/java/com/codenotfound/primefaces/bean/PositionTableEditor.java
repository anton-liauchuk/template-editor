package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.ColumnType;
import com.codenotfound.primefaces.PositionTable;
import com.codenotfound.primefaces.PositionTableColumnConfig;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@ViewScoped
public class PositionTableEditor {

    private PositionTable positionTable;
    private List<SelectItem> configs;
    private List<ColumnType> selectedColumns;

    public void reset(PositionTable positionTable) {
        this.positionTable = positionTable;
        configs = new ArrayList<>();
        positionTable.getColumnConfigs().forEach(columnConfig -> configs.add(new SelectItem(columnConfig.getColumnType())));
        selectedColumns = positionTable.getColumnConfigs()
                .stream()
                .filter(PositionTableColumnConfig::getEnabled)
                .map(PositionTableColumnConfig::getColumnType)
                .collect(Collectors.toList());
    }

    public void save() {
        positionTable.getColumnConfigs().forEach(config -> config.setEnabled(selectedColumns.contains(config.getColumnType())));
    }

    public PositionTable getPositionTable() {
        return positionTable;
    }

    public List<SelectItem> getConfigs() {
        return configs;
    }

    public List<ColumnType> getSelectedColumns() {
        return selectedColumns;
    }

    public void setSelectedColumns(List<ColumnType> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
}
