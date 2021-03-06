package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.*;
import com.codenotfound.primefaces.converter.EditableElementConverter;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class Editor {

    @EJB
    private EditableElementConverter editableElementConverter;

    private Template template;
    private List<EditableElement> editableElements;
    private List<DraggableComponent> draggableComponents;

    @PostConstruct
    public void init() {
        editableElements = new ArrayList<>();
        File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");

        Unmarshaller jaxbUnmarshaller;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Template.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            template = (Template) jaxbUnmarshaller.unmarshal(file);
            editableElements = template.getEditablePage().getEditableElements();

            for (EditableElement editableElement : editableElements) {
                if (editableElement instanceof PositionTable) {
                    PositionTable positionTable = (PositionTable) editableElement;
                    List<ColumnModel> displayedColumns = positionTable.getColumnConfigs().stream()
                            .filter(PositionTableColumnConfig::getEnabled)
                            .sorted(Comparator.comparingInt(PositionTableColumnConfig::getOrder))
                            .map(columnConfig -> new ColumnModel(columnConfig.getTitle(), columnConfig.getColumnType().getField(), columnConfig.getAlignment()))
                            .collect(Collectors.toList());
                    positionTable.setDisplayedColumns(displayedColumns);
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void sendPositions(ComponentList comps) {
        draggableComponents = comps.getComps();
    }

    public void save() {
        editableElements = editableElementConverter.convert(draggableComponents, editableElements);
        File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Template.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(template, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public void createPDF() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String serverName = externalContext.getRequestServerName();
        String port = String.valueOf(externalContext.getRequestServerPort());
        String appName = externalContext.getRequestContextPath();
        String protocol = externalContext.getRequestScheme();
        String url = protocol + "://" + serverName + ":" + port + appName + "/pdfTemplate.xhtml";
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(new URL(url).toString());
            renderer.layout();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"" + "pdfTemplate.pdf" + "\"");
            OutputStream browserStream = response.getOutputStream();
            renderer.createPDF(browserStream);

        } catch (Exception ex) {
            System.out.println("Exception during PDF Generation " + ex.getMessage());
        }
        facesContext.responseComplete();
    }

    public List<EditableElement> getEditableElements() {
        return editableElements;
    }
}
