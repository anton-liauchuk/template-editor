package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.ColumnModel;
import com.codenotfound.primefaces.PositionTable;
import com.codenotfound.primefaces.PositionTableColumnConfig;
import com.codenotfound.primefaces.converter.ColumnConverter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@ManagedBean
@ViewScoped
public class PositionTableEditor {

    private PositionTable positionTable;
    private List<PositionTableColumnConfig> configs;
    private List<PositionTableColumnConfig> selectedColumns;
    private ColumnConverter columnConverter;

    public void reset(PositionTable positionTable) {
        this.positionTable = positionTable;
        configs = positionTable.getColumnConfigs();
        selectedColumns = positionTable.getColumnConfigs()
                .stream()
                .filter(PositionTableColumnConfig::getEnabled)
                .collect(Collectors.toList());
        columnConverter = new ColumnConverter(configs);
    }

    public void save() {
        positionTable.getColumnConfigs().forEach(config -> config.setEnabled(selectedColumns.contains(config)));
        List<ColumnModel> displayedColumns = positionTable.getColumnConfigs().stream()
                .filter(PositionTableColumnConfig::getEnabled)
                .sorted(Comparator.comparingInt(PositionTableColumnConfig::getOrder))
                .map(columnConfig -> new ColumnModel(columnConfig.getTitle(), columnConfig.getColumnType().getField(), columnConfig.getAlignment()))
                .collect(Collectors.toList());
        positionTable.setDisplayedColumns(displayedColumns);
    }

    public List<PositionTableColumnConfig> getConfigs() {
        return configs;
    }

    public List<PositionTableColumnConfig> getSelectedColumns() {
        return selectedColumns;
    }

    public ColumnConverter getColumnConverter() {
        return columnConverter;
    }

    public void setSelectedColumns(List<PositionTableColumnConfig> selectedColumns) {
        this.selectedColumns = selectedColumns;
    }
}
