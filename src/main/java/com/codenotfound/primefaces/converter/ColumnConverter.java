package com.codenotfound.primefaces.converter;

import com.codenotfound.primefaces.PositionTableColumnConfig;
import org.primefaces.component.selectmanycheckbox.SelectManyCheckbox;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public class ColumnConverter implements Converter {

    private List<PositionTableColumnConfig> columnConfigs;

    public ColumnConverter(List<PositionTableColumnConfig> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        return ((PositionTableColumnConfig) object).getColumnType().toString();
    }

    @Override
    public PositionTableColumnConfig getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (component instanceof SelectManyCheckbox) {
            for (PositionTableColumnConfig columnConfig : columnConfigs) {
                if (columnConfig.getColumnType().toString().equals(submittedValue)) {
                    return columnConfig;
                }
            }
        }

        return null;
    }

}
