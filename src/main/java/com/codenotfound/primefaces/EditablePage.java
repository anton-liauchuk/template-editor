package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class EditablePage {

    private List<EditableElement> editableElements = new ArrayList<>();

    public List<EditableElement> getEditableElements() {
        return editableElements;
    }

    public void setEditableElements(List<EditableElement> editableElements) {
        this.editableElements = editableElements;
    }
}
