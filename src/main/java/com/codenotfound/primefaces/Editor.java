package com.codenotfound.primefaces;

import com.codenotfound.primefaces.converter.EditableElementConverter;
import com.sun.faces.facelets.component.UIRepeat;
import org.primefaces.component.outputpanel.OutputPanel;
import org.primefaces.component.panel.Panel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class Editor {

  @EJB
  private EditableElementConverter editableElementConverter;

  private EditablePage editablePage;
  private List<EditableElement> editableElements;
  private List<DraggableComponent> draggableComponents;
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

  public void sendPositions(ComponentList comps) {
    draggableComponents = comps.getComps();
  }

  public void save() {
    editableElements = editableElementConverter.convert(draggableComponents, editableElements);
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
