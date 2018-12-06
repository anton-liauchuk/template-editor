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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@ManagedBean
@ViewScoped
public class TemplatePreview {

    private List<EditableElement> editableElements;
    private Map<String, List<EditableElement>> positionedElements;
    private List<InvoicePosition> invoicePositions;
    private Double pageFixedTop;
    private Double pageFixedBottom;

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
            positionedElements = editableElements.stream().collect(groupingBy(EditableElement::getPosition));
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

            List<EditableElement> contentElements = positionedElements.get("content");
            pageFixedTop = contentElements.get(0).getTop();
            pageFixedBottom = 100 - (contentElements.get(contentElements.size() - 1).getTop() + contentElements.get(contentElements.size() - 1).getHeight());
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

    public Double getPageFixedTop() {
        return pageFixedTop;
    }

    public void setPageFixedTop(Double pageFixedTop) {
        this.pageFixedTop = pageFixedTop;
    }

    public Double getPageFixedBottom() {
        return pageFixedBottom;
    }

    public void setPageFixedBottom(Double pageFixedBottom) {
        this.pageFixedBottom = pageFixedBottom;
    }

    public Map<String, List<EditableElement>> getPositionedElements() {
        return positionedElements;
    }
}
