package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.NormalPanel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class NormalPanelEditor {

    private NormalPanel normalPanel;

    public void reset(NormalPanel normalPanel) {
        this.normalPanel = normalPanel;
    }

    public NormalPanel getNormalPanel() {
        return normalPanel;
    }
}
