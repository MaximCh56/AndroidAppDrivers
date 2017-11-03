package com.example.pk1.driverstable.model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerAnswer {

    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}