package com.codenotfound.primefaces;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class EditablePage {

    private List<EditableElement> editableElements = new ArrayList<>();

    public List<EditableElement> getEditableElements() {
        return editableElements;
    }

    @XmlElementRef
    public void setEditableElements(List<EditableElement> editableElements) {
        this.editableElements = editableElements;
    }
}
