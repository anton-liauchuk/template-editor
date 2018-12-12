package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.ColumnModel;
import com.codenotfound.primefaces.PositionTable;
import com.codenotfound.primefaces.PositionTableColumnConfig;
import com.codenotfound.primefaces.converter.ColumnConverter;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        positionTable.getColumnConfigs().forEach(config -> {
            Optional<PositionTableColumnConfig> editedColumn = selectedColumns.stream()
                    .filter(column -> column.getColumnType() == config.getColumnType()).findFirst();
            editedColumn.ifPresent(edited -> {
                config.setEnabled(true);
                config.setOrder(edited.getOrder());
            });

        });
        List<ColumnModel> displayedColumns = positionTable.getColumnConfigs().stream()
                .filter(PositionTableColumnConfig::getEnabled)
                .sorted(Comparator.comparingInt(PositionTableColumnConfig::getOrder))
                .map(columnConfig -> new ColumnModel(columnConfig.getTitle(), columnConfig.getColumnType().getField(), columnConfig.getAlignment()))
                .collect(Collectors.toList());
        positionTable.setDisplayedColumns(displayedColumns);
    }

    public void onRowReorder(AjaxBehaviorEvent event) {
        DataTable dataTable = (DataTable) event.getComponent();
        List<UIColumn> columns = dataTable.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            DynamicColumn dc = (DynamicColumn) columns.get(i);
            selectedColumns.get(dc.getIndex()).setOrder(i);
        }
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
