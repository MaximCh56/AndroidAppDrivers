package com.example.pk1.driverstable.presenter;


import com.example.pk1.driverstable.model.POJO.Driver;

public interface MainPresenter {
    void editDriver(Driver driver);
    void getCategory();
    void searchDrivers(String s);

}
