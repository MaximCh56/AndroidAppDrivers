package com.example.pk1.driverstable.view;

import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.POJO.Driver;

import java.util.List;


public interface MainView {
    void showMessage(String error);
    void showSelectDrivers(List<Driver> drivers);
    void updateDataDriverView();
    void showCategory(Category category);
}
