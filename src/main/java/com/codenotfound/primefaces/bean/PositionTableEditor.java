package com.codenotfound.primefaces.bean;

import com.codenotfound.primefaces.PositionTable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class PositionTableEditor {

    private PositionTable positionTable;

    public void reset(PositionTable positionTable) {
        this.positionTable = positionTable;
    }

    public PositionTable getPositionTable() {
        return positionTable;
    }
}
