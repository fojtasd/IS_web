package com.is_web.entities;

public class Locality implements Entity {

    int id;
    String label;
    String city;
    String street;
    String postal;
    String phone_office;
    int employees_count;
    Boolean is_deleted;

    public Locality(String label, String city, String street, String postal, String phone_office) {
        this.label = label;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.phone_office = phone_office;
        employees_count = 0;
        this.is_deleted = false;
    }

    public Locality() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setIs_deleted(Boolean is_deleted){this.is_deleted = is_deleted;}
    public Boolean getIs_deleted(){return is_deleted;}

    public String getPhone_office() {
        return phone_office;
    }

    public void setPhone_office(String phone_office) {
        this.phone_office = phone_office;
    }

    public int getEmployees_count() {
        return employees_count;
    }

    public void setEmployees_count(int employees_count) {
        this.employees_count = employees_count;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public String toString() {
        return "Locality{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postal='" + postal + '\'' +
                ", phone_office='" + phone_office + '\'' +
                ", employees_count='" + employees_count + '\'' +
                ", is_deleted='" + is_deleted + '\'' +
                '}';
    }
}
