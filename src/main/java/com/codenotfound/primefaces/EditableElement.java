package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({NormalPanel.class, PositionTable.class})
public class EditableElement {

    private Double width;
    private Double height;
    private Double left;
    private Double top;
    private String position;

    public Double getWidth() {
        return width;
    }

    @XmlElement
    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    @XmlElement
    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLeft() {
        return left;
    }

    @XmlElement
    public void setLeft(Double left) {
        this.left = left;
    }

    public Double getTop() {
        return top;
    }

    @XmlElement
    public void setTop(Double top) {
        this.top = top;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
