package com.codenotfound.primefaces;

import org.primefaces.component.outputpanel.OutputPanel;
import org.primefaces.component.panel.Panel;
import org.primefaces.event.DragDropEvent;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
public class Editor {

  private EditablePage editablePage;
  private List<EditableElement> editableElements;
  private OutputPanel pageTemplate;

  @PostConstruct
  public void init() {
    editableElements = new ArrayList<>();
    File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");

    Unmarshaller jaxbUnmarshaller;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(EditablePage.class);
      jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      editablePage = (EditablePage) jaxbUnmarshaller.unmarshal(file);
      editableElements = editablePage.getEditableElements();
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    System.out.println("save action");
    System.out.println(pageTemplate.getStyleClass());
    pageTemplate.getChildren().forEach(comp -> {
      if (comp instanceof Panel) {
        System.out.println(((Panel) comp).getStyle());
      }
    });
    File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(EditablePage.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(editablePage, file);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

  }

  public List<EditableElement> getEditableElements() {
    return editableElements;
  }

  public OutputPanel getPageTemplate() {
    return pageTemplate;
  }

  public void setPageTemplate(OutputPanel pageTemplate) {
    this.pageTemplate = pageTemplate;
  }
}
