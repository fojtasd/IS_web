package com.is_web.entities;

public class Company implements Entity {

    Integer id = null;
    String name;
    String vat;
    String city;
    String street;
    String postal;
    String phone;
    String email;
    boolean is_deleted;

    public Company() {
    }

    public Company(String name, String vat, String city, String street, String postal, String phone, String email) {
        this.id = id;
        this.name = name;
        this.vat = vat;
        this.city = city;
        this.street = street;
        this.postal = postal;
        this.phone = phone;
        this.email = email;
        this.is_deleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean getIsDeleted() {return is_deleted;};

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vat='" + vat + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postal='" + postal + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", is_deleted=" + is_deleted + '\'' +
                '}';
    }
}
