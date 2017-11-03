package com.example.pk1.driverstable.model.POJO;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Driver {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("patronymic")
    @Expose
    private String patronymic;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("sex")
    @Expose
    private Boolean sex;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;

        Driver driver = (Driver) o;

        if (!getUuid().equals(driver.getUuid())) return false;
        if (!getName().equals(driver.getName())) return false;
        if (!getSurname().equals(driver.getSurname())) return false;
        if (!getPatronymic().equals(driver.getPatronymic())) return false;
        if (!getCategory().equals(driver.getCategory())) return false;
        if (!getBirthDate().equals(driver.getBirthDate())) return false;
        return getSex().equals(driver.getSex());

    }

    @Override
    public int hashCode() {
        int result = getUuid().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getSurname().hashCode();
        result = 31 * result + getPatronymic().hashCode();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getBirthDate().hashCode();
        result = 31 * result + getSex().hashCode();
        return result;
    }
}