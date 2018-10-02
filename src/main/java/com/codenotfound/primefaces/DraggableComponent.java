package com.codenotfound.primefaces;

import java.io.Serializable;

public class DraggableComponent implements Serializable {

    private Integer index;
    private Integer left;
    private Integer top;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
