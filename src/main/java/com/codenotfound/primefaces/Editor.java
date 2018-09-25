package com.codenotfound.primefaces;

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

  public void onDrag(DragDropEvent ddEvent) {
    String draggedId = ddEvent.getDragId();
    String droppedId = ddEvent.getDropId();
    Object data = ddEvent.getData();
    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    String left = params.get(draggedId + "_left");
    String top = params.get(draggedId + "_top");
    System.out.println("left: " + left);
    System.out.println("top: " + top);
  }

  public List<EditableElement> getEditableElements() {
    return editableElements;
  }
}
