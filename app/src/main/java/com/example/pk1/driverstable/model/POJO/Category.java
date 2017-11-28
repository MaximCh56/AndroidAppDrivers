package com.example.pk1.driverstable.model.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category  {

    @SerializedName("category")
    @Expose
    private List<String> category = null;

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

}