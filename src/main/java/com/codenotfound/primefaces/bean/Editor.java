package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.ComponentList;
import com.codenotfound.primefaces.DraggableComponent;
import com.codenotfound.primefaces.EditableElement;
import com.codenotfound.primefaces.Template;
import com.codenotfound.primefaces.converter.EditableElementConverter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

  public List<EditableElement> getEditableElements() {
    return editableElements;
  }

  public Template getTemplate() {
    return template;
  }
}
