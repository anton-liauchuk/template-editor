package com.codenotfound.primefaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class Editor {

  private EditableElementList editableElementList;
  private List<EditableElement> editableElements;

  @PostConstruct
  public void init() {
    editableElements = new ArrayList<>();
    File file = new File("/home/lev/projects/jsf-primefaces-wildfly/src/main/resources/template.xml");

    Unmarshaller jaxbUnmarshaller;
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(EditableElementList.class);
      jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      editableElementList = (EditableElementList) jaxbUnmarshaller.unmarshal(file);
      editableElements = editableElementList.getEditableElements();
    } catch (JAXBException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    System.out.println("save action");
    EditableElement test = new EditableElement();
    test.setHeight(50);
    test.setWidth(50);
    test.setText("test");
    File file = new File("/home/lev/projects/template-editor/src/main/resources/template.xml");
    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(EditableElementList.class);
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
      jaxbMarshaller.marshal(editableElementList, file);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

  }

  public List<EditableElement> getEditableElements() {
    return editableElements;
  }
}
