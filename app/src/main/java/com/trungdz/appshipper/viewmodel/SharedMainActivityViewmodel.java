package com.trungdz.appshipper.viewmodel;

import androidx.lifecycle.ViewModel;

public class SharedMainActivityViewmodel extends ViewModel {

    public int getIdOrderIsSelected() {
        return idOrderIsSelected;
    }


    public void setIdOrderIsSelected(int idOrderIsSelected) {
        this.idOrderIsSelected = idOrderIsSelected;
    }

    int idOrderIsSelected;

}
