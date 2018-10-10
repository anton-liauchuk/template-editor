package com.codenotfound.primefaces;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Template {

    private EditablePage editablePage;
    private Header header;
    private Footer footer;

    public EditablePage getEditablePage() {
        return editablePage;
    }

    public void setEditablePage(EditablePage editablePage) {
        this.editablePage = editablePage;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }
}
