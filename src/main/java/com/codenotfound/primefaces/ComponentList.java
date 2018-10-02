package com.codenotfound.primefaces;

import java.io.Serializable;
import java.util.List;

public class ComponentList implements Serializable {

    private List<DraggableComponent> comps;

    public List<DraggableComponent> getComps() {
        return comps;
    }

    public void setComps(List<DraggableComponent> comps) {
        this.comps = comps;
    }
}
