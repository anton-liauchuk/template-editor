package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.*;
import com.codenotfound.primefaces.util.InvoicePositionUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class TemplatePreview {

    private List<EditableElement> editableElements;
    private List<InvoicePosition> invoicePositions;

    @PostConstruct
    public void init() {
        editableElements = new ArrayList<>();
        File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");

        Unmarshaller jaxbUnmarshaller;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Template template = (Template) jaxbUnmarshaller.unmarshal(file);
            editableElements = template.getEditablePage().getEditableElements();
            invoicePositions = InvoicePositionUtils.generate(2);

            for (EditableElement editableElement : editableElements) {
                if (editableElement instanceof PositionTable) {
                    PositionTable positionTable = (PositionTable) editableElement;
                    List<ColumnModel> displayedColumns = positionTable.getColumnConfigs().stream()
                            .filter(PositionTableColumnConfig::getEnabled)
                            .sorted(Comparator.comparingInt(PositionTableColumnConfig::getOrder))
                            .map(columnConfig -> new ColumnModel(columnConfig.getTitle(), columnConfig.getColumnType().getField()))
                            .collect(Collectors.toList());
                    positionTable.setDisplayedColumns(displayedColumns);
                }
            }

            editableElements.sort(Comparator.comparingDouble(EditableElement::getTop));
            for (int i = 0; i < editableElements.size(); i++) {
                if (i == 0) {
                    continue;
                }

                EditableElement previousElement = editableElements.get(i - 1);
                Double previousBottom = previousElement.getHeight() + previousElement.getTop();
                EditableElement currentElement = editableElements.get(i);
                currentElement.setTop(currentElement.getTop() - previousBottom);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<EditableElement> getEditableElements() {
        return editableElements;
    }

    public List<InvoicePosition> getInvoicePositions() {
        return invoicePositions;
    }
}
