package com.example.pk1.driverstable.presenter;


import android.view.View;

import com.example.pk1.driverstable.model.POJO.Driver;
import com.example.pk1.driverstable.view.MainView;

public interface MainPresenter {
    void editDriver(Driver driver);
    void getCategory();
    void searchDrivers(String s);
    void attach(MainView view);
    void detach();
    boolean isAttached();
}
