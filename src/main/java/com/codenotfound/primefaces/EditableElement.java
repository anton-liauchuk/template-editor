package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EditableElement {

    private Integer width;
    private Integer height;
    private String text;
    private Integer left;
    private Integer top;

    public Integer getWidth() {
        return width;
    }

    @XmlElement
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    @XmlElement
    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    @XmlElement
    public void setText(String text) {
        this.text = text;
    }

    public Integer getLeft() {
        return left;
    }

    @XmlElement
    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    @XmlElement
    public void setTop(Integer top) {
        this.top = top;
    }
}
