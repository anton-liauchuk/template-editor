package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.ColumnModel;
import com.codenotfound.primefaces.ColumnType;
import com.codenotfound.primefaces.PositionTable;
import com.codenotfound.primefaces.PositionTableColumnConfig;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Comparator;
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
        List<ColumnModel> displayedColumns = positionTable.getColumnConfigs().stream()
                .filter(PositionTableColumnConfig::getEnabled)
                .sorted(Comparator.comparingInt(PositionTableColumnConfig::getOrder))
                .map(columnConfig -> new ColumnModel(columnConfig.getTitle(), columnConfig.getColumnType().getField()))
                .collect(Collectors.toList());
        positionTable.setDisplayedColumns(displayedColumns);
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
