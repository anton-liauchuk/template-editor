package com.codenotfound.primefaces.converter;

import com.codenotfound.primefaces.DraggableComponent;
import com.codenotfound.primefaces.EditableElement;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;

@Stateless
public class EditableElementConverter {

    public List<EditableElement> convert(List<DraggableComponent> draggableComponents, List<EditableElement> editableElements) {
        for (int i = 0; i < editableElements.size(); i++) {
            EditableElement editableElement = editableElements.get(i);
            DraggableComponent draggableComponent = null;
            for (DraggableComponent component : draggableComponents) {
                if (Objects.equals(component.getIndex(), i)) {
                    draggableComponent = component;
                }
            }
            if (draggableComponent != null) {
                editableElement.setLeft(draggableComponent.getLeft());
                editableElement.setTop(draggableComponent.getTop());
            }
        }

        return editableElements;
    }

}
